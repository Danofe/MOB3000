package com.example.mob3000oblig.DataApi.DataModeller

data class Registrering(
    val fomTidspunkt: String,
    val kjoringensArt: com.example.mob3000oblig.DataApi.DataModeller.KjoringensArt,
    val registreringsstatus: com.example.mob3000oblig.DataApi.DataModeller.Registreringsstatus,
    val registrertForstegangPaEierskap: String,
    val vektarsavgiftOppgittGrunnlag: com.example.mob3000oblig.DataApi.DataModeller.VektarsavgiftOppgittGrunnlag
)