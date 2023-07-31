<?php

namespace App\Http\Controllers;

use App\Models\paciente;
use Illuminate\Http\Request;

class PacienteControler extends Controller
{
    public function save(Request $req)
    {
        if($req->id ==0){
        $paciente = new paciente();

    }else{
        $paciente =paciente::find($req->id);
    }

    $paciente->nombre = $req->nombre;
    $paciente->nss = $req->nss;
    $paciente->tipo_sangre = $req->tipo_sangre;
    $paciente->alergias = $req->alergias;
    $paciente->telefono = $req->telefono;
    $paciente->domicilio = $req->domicilio;

    $paciente->save();
    return "Ok";
}

    public function list(Request $req)
    {
        $pacientes = paciente::all();
        return $pacientes;

    }
    public function delete(Request $req)
    {
        $pacientes = paciente::find($req->id);
        $pacientes->delete();
        return "Ok";

    }
}
