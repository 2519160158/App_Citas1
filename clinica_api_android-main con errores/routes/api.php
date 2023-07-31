<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\pacienteController;
use App\Http\Controllers\doctorController;
use App\Http\Controllers\enfermedadController;
use App\Http\Controllers\citaController;
use App\Http\Controllers\login_controller;

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

Route::get('/pacientes', [pacienteController::class, 'list']);
//Route::get('/paciente', [pacienteController::class, 'list']);
Route::post('/paciente', [pacienteController::class, 'save']);
Route::post('/paciente/delete', [pacienteController::class, 'delete']);

Route::get('/doctores', [doctorController::class, 'list']);
//Route::get('/paciente', [pacienteController::class, 'list']);
Route::post('/doctor', [doctorController::class, 'save']);
Route::post('/doctor/delete', [doctorController::class, 'delete']);

Route::get('/enfermedades', [enfermedadController::class, 'list']);
//Route::get('/paciente', [pacienteController::class, 'list']);
Route::post('/enfermedad', [enfermedadController::class, 'save']);
Route::post('/enfermedad/delete', [enfermedadController::class, 'delete']);

Route::get('/citas', [citaController::class, 'list']);
//Route::get('/paciente', [pacienteController::class, 'list']);
Route::post('/cita', [citaController::class, 'save']);
Route::post('/cita/delete', [citaController::class, 'delete']);

/*
Route::get('user'), function () {
    return User::all();
});
*/
