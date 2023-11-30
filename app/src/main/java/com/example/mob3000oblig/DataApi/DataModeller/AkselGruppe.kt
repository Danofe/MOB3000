package com.example.mob3000oblig.DataApi.DataModeller

data class AkselGruppe(
    val akselListe: com.example.mob3000oblig.DataApi.DataModeller.AkselListe,
    val id: Int,
    val plasseringAkselGruppe: String,
    val tekniskTillattAkselGruppeLast: Int
)