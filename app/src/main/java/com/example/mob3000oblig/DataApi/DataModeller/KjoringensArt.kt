package com.example.mob3000oblig.DataApi.DataModeller

data class KjoringensArt(
    val kodeBeskrivelse: String,
    val kodeNavn: String,
    val kodeVerdi: String,
    val tidligereKodeVerdi: List<Any>
)