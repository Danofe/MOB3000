package com.example.mob3000oblig.DataApi.DataModeller

data class TekniskGodkjenning(
    val godkjenningsId: String,
    val godkjenningsundertype: com.example.mob3000oblig.DataApi.DataModeller.Godkjenningsundertype,
    val gyldigFraDato: String,
    val gyldigFraDatoTid: String,
    val kjoretoyklassifisering: com.example.mob3000oblig.DataApi.DataModeller.Kjoretoyklassifisering,
    val krav: List<com.example.mob3000oblig.DataApi.DataModeller.Krav>,
    val tekniskeData: com.example.mob3000oblig.DataApi.DataModeller.TekniskeData,
    val unntak: List<Any>
)