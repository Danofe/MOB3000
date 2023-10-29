package com.example.mob3000oblig.DataModeller

data class Miljodata(
    val euroKlasse: EuroKlasse,
    val miljoOgdrivstoffGruppe: List<MiljoOgdrivstoffGruppe>,
    val okoInnovasjon: Boolean
)