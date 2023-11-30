package com.example.mob3000oblig.DataApi.DataModeller

data class Kjennemerke(
    val fomTidspunkt: String,
    val kjennemerke: String,
    val kjennemerkekategori: String,
    val kjennemerketype: com.example.mob3000oblig.DataApi.DataModeller.Kjennemerketype
)