<?php

namespace App\Http\Controllers;

use App\Models\enfermedad;
use Illuminate\Http\Request;

class EnfermedadControler extends Controller
{
    public function save(Request $req)
    {
        if($req->id ==0){
        $enfermedad = new enfermedad();

    }else{
        $enfermedad =enfermedad::find($req->id);
    }

    $enfermedad->nombre = $req->nombre;
    $enfermedad->tipo = $req->tipo;
    $enfermedad->Descripcion = $req->Descripcion;

    $enfermedad->save();
    return "Ok";
}

    public function list(Request $req)
    {
        $enfermedades = enfermedad::all();
        return $enfermedades;

    }
    public function delete(Request $req)
    {
        $enfermedades = enfermedad::find($req->id);
        $enfermedades->delete();
        return "Ok";

    }
}

