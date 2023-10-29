package com.example.mob3000oblig.DataModeller

data class Motor(
    val arbeidsprinsipp: Arbeidsprinsipp,
    val drivstoff: List<Drivstoff>,
    val motorKode: String
)