package com.brezker.myapplication.extras

class Models {
    data class RespLogin(
        var idUsr:Int,
        var token:String,
        var nombre:String,
        var error:String,
    )
    data class Paciente(
        var id : Int,
        var nombre: String,
        var nss: String,
        var tipo_sangre: String,
        var alergias: String,
        var telefono: String,
        var domicilio: String,
    )
    {
        override fun toString(): String {
            return nombre
        }
    }

    data class Doctor(
        var id : Int,
        var nombre: String,
        var cedula: String,
        var especialidad: String,
        var turno: String,
        var telefono: String,
        var email: String,
    )

    {
        override fun toString(): String {
            return nombre
        }
    }

    data class Enfermedad(
        var id : Int,
        var nombre: String,
        var tipo: String,
        var Descripcion: String,
    )
    {
        override fun toString(): String {
            return nombre
        }
    }
    data class Cita(
        var id : Int,
        var id_enfermedad: String,
        var id_paciente: String,
        var id_medico: String,
        var consultorio: String,
        var fecha: String,
        var nombre_enfermedad: String,
        var nombre_paciente: String,
        var nombre_medico: String,
    ){
        override fun toString(): String {
            return consultorio
        }
    }

    }