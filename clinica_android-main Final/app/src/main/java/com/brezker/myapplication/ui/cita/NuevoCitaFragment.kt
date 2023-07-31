package com.brezker.myapplication.ui.cita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.brezker.myapplication.R
import com.brezker.myapplication.databinding.FragmentNuevoCitaBinding
import com.brezker.myapplication.databinding.FragmentNuevoDoctorBinding
import com.brezker.myapplication.extras.Models
import com.brezker.myapplication.extras.VariablesGlobales
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import android.widget.DatePicker


private const val ARG_PARAM1 = "json_cita"
private var id_cita: Int = 0
private var idDoctor: Int = 0
private var idPaciente: Int = 0
private var idEnfermedad: Int = 0
private var selectedType: String = ""

private lateinit var binding: FragmentNuevoCitaBinding
private lateinit var spinner: Spinner
private lateinit var datePicker: DatePicker


class NuevoCitaFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var json_cita: String? = null
    private var param2: String? = null

    private var _binding: FragmentNuevoCitaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            json_cita = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNuevoCitaBinding.inflate(inflater, container, false)
        val view = binding.root

        obtenerDoctor()
        obtenerPaciente()
        obtenerEnfermedad()
        datePicker = view.findViewById(R.id.edtFecha)

        val year = datePicker.year
        val month = datePicker.month + 1  // Los meses en DatePicker son zero-based, por lo que sumamos 1
        val dayOfMonth = datePicker.dayOfMonth

        val formattedDate = String.format("%04d/%02d/%02d ", dayOfMonth, month, year)

        if (json_cita != null) {
            var gson = Gson()
            var objCita = gson.fromJson(json_cita, Models.Cita::class.java)

            id_cita = objCita.id
            binding.edtConsultorio.setText(objCita.consultorio)



            val arrayPaciente = resources.getStringArray(R.array.id_paciente)
            val arrayDoctor = resources.getStringArray(R.array.id_medico)
            val arrayEnfermedad = resources.getStringArray(R.array.id_enfermedad)

            var countPaciente = 0
            var countDoctor = 0
            var countEnfermedad = 0

            for (item in arrayPaciente) {
                if (item == objCita.id_paciente) {
                    binding.spiPaciente.setSelection(countPaciente)
                    idPaciente = item.toInt()
                    break
                }
                countPaciente++
            }

            for (item in arrayDoctor) {
                if (item == objCita.id_medico) {
                    binding.spiDoctor.setSelection(countDoctor)
                    idDoctor = item.toInt()
                    break
                }
                countDoctor++
            }

            for (item in arrayEnfermedad) {
                if (item == objCita.id_enfermedad) {
                    binding.spiEnfermedad.setSelection(countEnfermedad)
                    idEnfermedad = item.toInt()
                    break
                }
                countEnfermedad++
            }
        }

        binding.spiDoctor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 1) {
                    val selectedDoctor = parent?.getItemAtPosition(position) as Models.Doctor
                    idDoctor = selectedDoctor.id
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó ningún valor
            }
        }

        binding.spiPaciente.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 1) {
                    val selectedItem = parent?.getItemAtPosition(position)
                    if (selectedItem is Models.Paciente) {
                        val selectedPaciente = selectedItem
                        idDoctor = selectedPaciente.id
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó ningún valor
            }
        }



        binding.spiEnfermedad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 1) {
                    val selectedItem = parent?.getItemAtPosition(position)
                    if (selectedItem is Models.Enfermedad) {
                        val selectedEnfermedad = selectedItem
                        idEnfermedad = selectedEnfermedad.id
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó ningún valor
            }
        }



        binding.btnGuardar.setOnClickListener {
            guardarDatos()
        }
        binding.btnEliminar.setOnClickListener {
            eliminarDatos()
        }
        return view
    }

    private fun guardarDatos() {
        val client = OkHttpClient()

        val formBody: RequestBody = FormBody.Builder()
            .add("id", id_cita.toString())
            .add("consultorio", binding.edtConsultorio.text.toString())
            .add("fecha", obtenerFechaSeleccionada())
            .add("id_medico", idDoctor.toString())
            .add("id_paciente", idPaciente.toString())
            .add("id_enfermedad", idEnfermedad.toString())
            .build()

        val request = Request.Builder()
            .url(VariablesGlobales.citaUrl)
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "Ocurrió un error: " + e.message, Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println(respuesta)
                activity?.runOnUiThread {
                    activity?.onBackPressed()
                }
            }
        })
    }

    private fun obtenerFechaSeleccionada(): String {
        val year = datePicker.year
        val month = datePicker.month + 1  // Los meses en DatePicker son zero-based, por lo que sumamos 1
        val dayOfMonth = datePicker.dayOfMonth

        return String.format("%04d/%02d/%02d 00:00:00", year, month, dayOfMonth)
    }




    fun eliminarDatos() {
        val client = OkHttpClient()

        val formBody: RequestBody = FormBody.Builder()
            .add("id", id_cita.toString())
            .build()

        val request = Request.Builder()
            .url(VariablesGlobales.citaBorrarUrl)
            .post(formBody)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "Ocurrio un error: " + e.message, Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println(respuesta)
                activity?.runOnUiThread {
                    activity?.onBackPressed()
                }
            }
        })
    }

    private fun obtenerDoctor() {
        val url = VariablesGlobales.medicosUrl

        val request = Request.Builder().url(url).get().build()
        val client = OkHttpClient()
        val objGson = Gson()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejo de error
            }

            override fun onResponse(call: Call, response: Response) {
                val respuesta = response.body?.string()

                val listaDoctores = objGson.fromJson(respuesta, Array<Models.Doctor>::class.java)

                if (listaDoctores != null) {
                    val adapter: ArrayAdapter<Models.Doctor> = ArrayAdapter(
                        requireActivity().baseContext,
                        android.R.layout.simple_spinner_item,
                        listaDoctores
                    )

                    activity?.runOnUiThread {
                        binding.spiDoctor.adapter = adapter
                    }
                }
            }
        })
    }

    private fun obtenerPaciente() {
        val url = VariablesGlobales.pacientesUrl

        val request = Request.Builder().url(url).get().build()
        val client = OkHttpClient()
        val objGson = Gson()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejo de error
            }

            override fun onResponse(call: Call, response: Response) {
                val respuesta = response.body?.string()

                val listaPacientes = objGson.fromJson(respuesta, Array<Models.Paciente>::class.java)
                if (listaPacientes != null) {
                    val adapter: ArrayAdapter<Models.Paciente> = ArrayAdapter(
                        requireActivity().baseContext,
                        android.R.layout.simple_spinner_item,
                        listaPacientes
                    )

                    activity?.runOnUiThread {
                        binding.spiPaciente.adapter = adapter
                    }
                }
            }
        })
    }

    private fun obtenerEnfermedad() {
        val url = VariablesGlobales.enfermedadesUrl

        val request = Request.Builder().url(url).get().build()
        val client = OkHttpClient()
        val objGson = Gson()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejo de error
            }

            override fun onResponse(call: Call, response: Response) {
                val respuesta = response.body?.string()

                val listaEnfermedad = objGson.fromJson(respuesta, Array<Models.Enfermedad>::class.java)

                if (listaEnfermedad != null) {
                    val adapter: ArrayAdapter<Models.Enfermedad> = ArrayAdapter(
                        requireActivity().baseContext,
                        android.R.layout.simple_spinner_item,
                        listaEnfermedad
                    )

                    activity?.runOnUiThread {
                        binding.spiEnfermedad.adapter = adapter
                    }
                }
            }
        })
    }

    private fun getDoctorPosition(adapter: ArrayAdapter<Models.Doctor>, doctorName: String): Int {
        for (index in 0 until adapter.count) {
            val doctor = adapter.getItem(index)
            if (doctor?.nombre == doctorName) {
                return index
            }
        }
        return 0
    }

    private fun getPacientePosition(adapter: ArrayAdapter<Models.Paciente>, pacienteName: String): Int {
        for (index in 0 until adapter.count) {
            val paciente = adapter.getItem(index)
            if (paciente?.nombre == pacienteName) {
                return index
            }
        }
        return 0
    }

    private fun getEnfermedadPosition(adapter: ArrayAdapter<Models.Enfermedad>, enfermedadName: String): Int {
        for (index in 0 until adapter.count) {
            val enfermedad = adapter.getItem(index)
            if (enfermedad?.nombre == enfermedadName) {
                return index
            }
        }
        return 0
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NuevoCitaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevoCitaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}