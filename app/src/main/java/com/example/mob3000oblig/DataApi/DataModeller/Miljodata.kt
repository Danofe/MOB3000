package com.example.mob3000oblig.DataApi.DataModeller

data class Miljodata(
    val euroKlasse: com.example.mob3000oblig.DataApi.DataModeller.EuroKlasse,
    val miljoOgdrivstoffGruppe: List<com.example.mob3000oblig.DataApi.DataModeller.MiljoOgdrivstoffGruppe>,
    val okoInnovasjon: Boolean
)