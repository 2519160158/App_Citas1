<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations
     */
    public function up(): void
    {
        Schema::create('cita', function (Blueprint $table) {
            $table->id();
            $table->biginteger('id_enfermedad');
            $table->biginteger('id_paciente');
            $table->biginteger('id_doctor');
            $table->foreign('id_enfermedad')->references('id')->on('enfermedad')->onDelete('cascade');
            $table->foreign('id_paciente')->references('id')->on('paciente')->onDelete('cascade');
            $table->foreign('id_doctor')->references('id')->on('doctor')->onDelete('cascade');
            $table->string('consultorio');
            $table->string('domicilio');
            $table->datetime('fecha');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('cita');
    }
};
