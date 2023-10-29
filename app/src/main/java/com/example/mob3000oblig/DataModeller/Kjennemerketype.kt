package com.example.mob3000oblig.DataModeller

data class Kjennemerketype(
    val kodeBeskrivelse: String,
    val kodeNavn: String,
    val kodeVerdi: String,
    val tidligereKodeVerdi: List<Any>
)