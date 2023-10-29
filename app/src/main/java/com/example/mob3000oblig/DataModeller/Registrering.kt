package com.example.mob3000oblig.DataModeller

data class Registrering(
    val fomTidspunkt: String,
    val kjoringensArt: KjoringensArt,
    val registreringsstatus: Registreringsstatus,
    val registrertForstegangPaEierskap: String,
    val vektarsavgiftOppgittGrunnlag: VektarsavgiftOppgittGrunnlag
)