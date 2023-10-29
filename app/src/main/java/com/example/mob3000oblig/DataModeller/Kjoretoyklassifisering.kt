package com.example.mob3000oblig.DataModeller

data class Kjoretoyklassifisering(
    val beskrivelse: String,
    val efTypegodkjenning: EfTypegodkjenning,
    val iSamsvarMedTypegodkjenning: Boolean,
    val kjoretoyAvgiftsKode: KjoretoyAvgiftsKode,
    val nasjonalGodkjenning: NasjonalGodkjenning,
    val spesielleKjennetegn: String,
    val tekniskKode: TekniskKode,
    val tekniskUnderkode: TekniskUnderkode
)