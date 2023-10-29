package com.example.mob3000oblig.DataModeller

data class TekniskGodkjenning(
    val godkjenningsId: String,
    val godkjenningsundertype: Godkjenningsundertype,
    val gyldigFraDato: String,
    val gyldigFraDatoTid: String,
    val kjoretoyklassifisering: Kjoretoyklassifisering,
    val krav: List<Krav>,
    val tekniskeData: TekniskeData,
    val unntak: List<Any>
)