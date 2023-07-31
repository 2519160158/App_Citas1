package com.brezker.myapplication.extras

object VariablesGlobales {
        private const val BASE_URL = "http://192.168.0.3:8000/api/"
        //private const val BASE_URL = "http://10.10.48.1:8000/api/"

        val loginUrl: String = BASE_URL + "login"

        //ver datos

        val pacientesUrl: String = BASE_URL + "pacientes"
        val medicosUrl: String = BASE_URL + "medicos"
        val enfermedadesUrl: String = BASE_URL + "enfermedades"
        val citasUrl: String = BASE_URL + "citas"

        val pacienteBorrarUrl: String = BASE_URL + "paciente/borrar"
        val medicoBorrarUrl: String = BASE_URL + "medico/borrar"
        val enfermedadBorrarUrl: String = BASE_URL + "enfermedad/borrar"
        val citaBorrarUrl: String = BASE_URL + "cita/borrar"

        //guardar

        val pacienteUrl: String = BASE_URL + "paciente"
        val medicoUrl: String = BASE_URL + "medico"
        val enfermedadUrl: String = BASE_URL + "enfermedad"
        val citaUrl: String = BASE_URL + "cita"
}