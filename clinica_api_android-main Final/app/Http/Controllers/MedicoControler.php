<?php

namespace App\Http\Controllers;

use App\Models\medico;
use Illuminate\Http\Request;

class MedicoControler extends Controller
{
    public function save(Request $req)
    {
        if($req->id ==0){
        $medico = new medico();

    }else{
        $medico =medico::find($req->id);
    }

    $medico->nombre = $req->nombre;
    $medico->cedula = $req->cedula;
    $medico->especialidad = $req->especialidad;
    $medico->turno = $req->turno;
    $medico->telefono = $req->telefono;
    $medico->email = $req->email;

    $medico->save();
    return "Ok";
}

    public function list(Request $req)
    {
        $medicos = medico::all();
        return $medicos;

    }
    public function delete(Request $req)
    {
        $medicos = medico::find($req->id);
        $medicos->delete();
        return "Ok";

    }
}

