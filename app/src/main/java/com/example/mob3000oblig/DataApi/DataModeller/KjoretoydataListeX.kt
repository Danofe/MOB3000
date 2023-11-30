package com.example.mob3000oblig.DataApi.DataModeller

data class KjoretoydataListeX(
    val forstegangsregistrering: com.example.mob3000oblig.DataApi.DataModeller.Forstegangsregistrering,
    val godkjenning: com.example.mob3000oblig.DataApi.DataModeller.Godkjenning,
    val kjennemerke: List<com.example.mob3000oblig.DataApi.DataModeller.Kjennemerke>,
    val kjoretoyId: com.example.mob3000oblig.DataApi.DataModeller.KjoretoyId,
    val periodiskKjoretoyKontroll: com.example.mob3000oblig.DataApi.DataModeller.PeriodiskKjoretoyKontroll,
    val registrering: com.example.mob3000oblig.DataApi.DataModeller.Registrering
)