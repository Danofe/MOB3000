package com.example.mob3000oblig.DataApi.DataModeller

data class MiljoOgdrivstoffGruppe(
    val drivstoffKodeMiljodata: com.example.mob3000oblig.DataApi.DataModeller.DrivstoffKodeMiljodata,
    val forbrukOgUtslipp: List<com.example.mob3000oblig.DataApi.DataModeller.ForbrukOgUtslipp>,
    val lyd: com.example.mob3000oblig.DataApi.DataModeller.Lyd
)