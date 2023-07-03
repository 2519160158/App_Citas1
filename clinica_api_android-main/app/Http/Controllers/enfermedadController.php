<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\m_enfermedad;

class enfermedadController extends Controller
{
    public function save(Request $req){
        if ($req->id == 0) {
            $enfermedad = new m_enfermedad();
        } else {
            $enfermedad = m_enfermedad::find($req->id);
        }

        $enfermedad->nombre = $req->nombre;
        $enfermedad->tipo = $req->tipo;
        $enfermedad->descripcion = $req->descripcion;

        $enfermedad->save();
    }

    public function list(Request $req){
        $enfermedades = m_enfermedad::all();
        return $enfermedades;
        //return "Ok"; //cuidado con como se pone en android
    }

    public function delete(Request $req){
        $enfermedad = m_enfermedad::find($req->id);
        $enfermedad->delete();
        return "Ok"; //cuidado con como se pone en android
    }
}
