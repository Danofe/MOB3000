package com.example.mob3000oblig.DataApi.DataModeller

data class PlasseringUnderstellsnummer(
    val kodeBeskrivelse: String,
    val kodeNavn: String,
    val kodeTypeId: String,
    val kodeVerdi: String,
    val tidligereKodeVerdi: List<Any>
)