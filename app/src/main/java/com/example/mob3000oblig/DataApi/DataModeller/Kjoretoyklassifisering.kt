package com.example.mob3000oblig.DataApi.DataModeller

data class Kjoretoyklassifisering(
    val beskrivelse: String,
    val efTypegodkjenning: com.example.mob3000oblig.DataApi.DataModeller.EfTypegodkjenning,
    val iSamsvarMedTypegodkjenning: Boolean,
    val kjoretoyAvgiftsKode: com.example.mob3000oblig.DataApi.DataModeller.KjoretoyAvgiftsKode,
    val nasjonalGodkjenning: com.example.mob3000oblig.DataApi.DataModeller.NasjonalGodkjenning,
    val spesielleKjennetegn: String,
    val tekniskKode: com.example.mob3000oblig.DataApi.DataModeller.TekniskKode,
    val tekniskUnderkode: com.example.mob3000oblig.DataApi.DataModeller.TekniskUnderkode
)