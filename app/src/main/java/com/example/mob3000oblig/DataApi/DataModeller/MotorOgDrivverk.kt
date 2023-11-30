package com.example.mob3000oblig.DataApi.DataModeller

data class MotorOgDrivverk(
    val girkassetype: com.example.mob3000oblig.DataApi.DataModeller.Girkassetype,
    val girutvekslingPrGir: List<Any>,
    val hybridKategori: com.example.mob3000oblig.DataApi.DataModeller.HybridKategori,
    val maksimumHastighet: List<Int>,
    val maksimumHastighetMalt: List<Any>,
    val motor: List<com.example.mob3000oblig.DataApi.DataModeller.Motor>
)