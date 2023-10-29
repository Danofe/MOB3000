package com.example.mob3000oblig.DataModeller

data class Vekter(
    val egenvekt: Int,
    val egenvektMinimum: Int,
    val nyttelast: Int,
    val tillattTilhengervektMedBrems: Int,
    val tillattTilhengervektUtenBrems: Int,
    val tillattTotalvekt: Int,
    val tillattVertikalKoplingslast: Int,
    val tillattVogntogvekt: Int,
    val vogntogvektAvhBremsesystem: List<Any>
)