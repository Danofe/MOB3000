package com.example.mob3000oblig.DataModeller

data class MiljoOgdrivstoffGruppe(
    val drivstoffKodeMiljodata: DrivstoffKodeMiljodata,
    val forbrukOgUtslipp: List<ForbrukOgUtslipp>,
    val lyd: Lyd
)