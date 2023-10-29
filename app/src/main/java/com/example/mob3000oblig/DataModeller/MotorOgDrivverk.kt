package com.example.mob3000oblig.DataModeller

data class MotorOgDrivverk(
    val girkassetype: Girkassetype,
    val girutvekslingPrGir: List<Any>,
    val hybridKategori: HybridKategori,
    val maksimumHastighet: List<Int>,
    val maksimumHastighetMalt: List<Any>,
    val motor: List<Motor>
)