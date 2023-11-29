package com.example.mob3000oblig.DataApi.DataModeller

data class StoyMalingOppgittAv(
    val kodeBeskrivelse: String,
    val kodeNavn: String,
    val kodeTypeId: String,
    val kodeVerdi: String,
    val tidligereKodeVerdi: List<Any>
)