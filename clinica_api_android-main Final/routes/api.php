<?php

use App\Http\Controllers\EnfermedadControler;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\login_controller;
use App\Http\Controllers\MedicoControler;
use App\Http\Controllers\PacienteControler;
use App\Http\Controllers\CitaControler;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('login', [login_controller::class, 'login']);

Route::get('/pacientes', [PacienteControler::class, 'list']);
Route::post('/paciente', [PacienteControler::class, 'save']);
Route::post('/paciente/borrar', [PacienteControler::class, 'delete']);

Route::get('/enfermedades', [EnfermedadControler::class, 'list']);
Route::post('/enfermedad', [EnfermedadControler::class, 'save']);
Route::post('/enfermedad/borrar', [EnfermedadControler::class, 'delete']);

Route::get('/medicos', [MedicoControler::class, 'list']);
Route::post('/medico', [MedicoControler::class, 'save']);
Route::post('/medico/borrar', [MedicoControler::class, 'delete']);

Route::get('/citas', [CitaControler::class, 'list']);
Route::post('/cita', [CitaControler::class, 'save']);
Route::post('/cita/borrar', [CitaControler::class, 'delete']);