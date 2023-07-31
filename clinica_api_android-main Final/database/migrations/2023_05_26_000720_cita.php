<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        //
        Schema::create('cita', function (Blueprint $table){
                $table->id();

                $table->bigInteger('id_enfermedad');
                $table->bigInteger('id_paciente');
                $table->bigInteger('id_medico');

                $table->string('consultorio');
                $table->dateTime('fecha');

                $table->timestamps();

                $table->foreign('id_enfermedad')->references('id')->on('enfermedad');
                $table->foreign('id_paciente')->references('id')->on('paciente');
                $table->foreign('id_medico')->references('id')->on('medico');


        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        //
        Schema::dropIfExists('cita');
    }
};
