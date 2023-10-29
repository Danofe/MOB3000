package com.example.mob3000oblig.DataModeller

data class Kravoppfyllelse(
    val kodeBeskrivelse: String,
    val kodeVerdi: String,
    val tidligereKodeVerdi: List<Any>
)