package com.example.mob3000oblig.DataApi.DataModeller

data class Motor(
    val arbeidsprinsipp: com.example.mob3000oblig.DataApi.DataModeller.Arbeidsprinsipp,
    val drivstoff: List<com.example.mob3000oblig.DataApi.DataModeller.Drivstoff>,
    val motorKode: String
)