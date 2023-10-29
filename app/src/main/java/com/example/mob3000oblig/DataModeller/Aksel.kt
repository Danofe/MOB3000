package com.example.mob3000oblig.DataModeller

data class Aksel(
    val antallHjul: Int,
    val avstandTilNesteAksling: Int,
    val drivAksel: Boolean,
    val id: Int,
    val plasseringAksel: String,
    val sporvidde: Int,
    val styreAksel: Boolean,
    val tekniskTillattAkselLast: Int
)