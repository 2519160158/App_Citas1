<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\m_paciente;

class pacienteController extends Controller
{
    public function save(Request $req){
        if ($req->id == 0) {
            $paciente = new m_paciente();
        } else {
            $paciente = m_paciente::find($req->id);
        }

        $paciente->nombre = $req->nombre;
        $paciente->nss = $req->nss;
        $paciente->tipo_sangre = $req->tipo_sangre;
        $paciente->alergias = $req->alergias;
        $paciente->telefono = $req->telefono;
        $paciente->domicilio = $req->domicilio;

        $paciente->save();
    }

    public function list(Request $req){
        $pacientes = m_paciente::all();
        return $pacientes;
        //return "Ok"; //cuidado con como se pone en android
    }

    public function delete(Request $req){
        $paciente = m_paciente::find($req->id);
        $paciente->delete();
        return "Ok"; //cuidado con como se pone en android
    }
}