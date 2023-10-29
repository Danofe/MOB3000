package com.example.mob3000oblig.DataModeller

data class ForstegangsGodkjenning(
    val forstegangRegistrertDato: String,
    val fortollingOgMva: FortollingOgMva,
    val godkjenningsId: String,
    val godkjenningsundertype: Godkjenningsundertype,
    val gyldigFraDato: String,
    val gyldigFraDatoTid: String,
    val unntak: List<Any>
)