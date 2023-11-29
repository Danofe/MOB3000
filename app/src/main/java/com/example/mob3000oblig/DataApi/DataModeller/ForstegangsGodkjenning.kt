package com.example.mob3000oblig.DataApi.DataModeller

data class ForstegangsGodkjenning(
    val forstegangRegistrertDato: String,
    val fortollingOgMva: com.example.mob3000oblig.DataApi.DataModeller.FortollingOgMva,
    val godkjenningsId: String,
    val godkjenningsundertype: com.example.mob3000oblig.DataApi.DataModeller.Godkjenningsundertype,
    val gyldigFraDato: String,
    val gyldigFraDatoTid: String,
    val unntak: List<Any>
)