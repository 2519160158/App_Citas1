<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\cita;

class CitaControler extends Controller
{
    public function list(){
        $citas = cita::join('enfermedad','cita.id_enfermedad','=','enfermedad.id')
        ->join('paciente','cita.id_paciente','=','paciente.id')
        ->join('medico','cita.id_medico','=','medico.id')
        ->select('cita.*', 'enfermedad.nombre as nombre_enfermedad', 'paciente.nombre as nombre_paciente', 'medico.nombre as nombre_medico')
        ->get(); 
        return $citas;
    }
    public function save(Request $req){
        if ($req->id == 0) {
            $cita = new cita();
        } else {
            $cita = cita::find($req->id);
        }

        $cita->id_enfermedad = $req->id_enfermedad;
        $cita->id_paciente = $req->id_paciente;
        $cita->id_medico = $req->id_medico;
        $cita->consultorio = $req->consultorio;
        $cita->fecha = $req->fecha;

        $cita->save();
    }

    public function lista(Request $req){
        $citas = cita::all();
        return $citas;
        //return "Ok"; //cuidado con como se pone en android
    }

    public function delete(Request $req){
        $cita = cita::find($req->id);
        $cita->delete();
        return "Ok"; //cuidado con como se pone en android
    }
}

