package com.example.mob3000oblig.DataModeller

data class KjoretoydataListeX(
    val forstegangsregistrering: Forstegangsregistrering,
    val godkjenning: Godkjenning,
    val kjennemerke: List<Kjennemerke>,
    val kjoretoyId: KjoretoyId,
    val periodiskKjoretoyKontroll: PeriodiskKjoretoyKontroll,
    val registrering: Registrering
)