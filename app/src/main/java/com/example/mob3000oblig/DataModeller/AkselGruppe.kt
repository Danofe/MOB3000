package com.example.mob3000oblig.DataModeller

data class AkselGruppe(
    val akselListe: AkselListe,
    val id: Int,
    val plasseringAkselGruppe: String,
    val tekniskTillattAkselGruppeLast: Int
)