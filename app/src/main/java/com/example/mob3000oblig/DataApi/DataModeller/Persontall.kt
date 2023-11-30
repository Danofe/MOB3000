package com.example.mob3000oblig.DataApi.DataModeller

data class Persontall(
    val sitteplassListe: com.example.mob3000oblig.DataApi.DataModeller.SitteplassListe,
    val sitteplasserForan: Int,
    val sitteplasserTotalt: Int
)