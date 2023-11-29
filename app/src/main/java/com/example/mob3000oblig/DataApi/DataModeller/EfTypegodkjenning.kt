package com.example.mob3000oblig.DataApi.DataModeller

data class EfTypegodkjenning(
    val typegodkjenningNrTekst: String,
    val typegodkjenningnummer: com.example.mob3000oblig.DataApi.DataModeller.Typegodkjenningnummer,
    val variant: String,
    val versjon: String
)