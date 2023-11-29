package com.example.mob3000oblig.DataApi.DataModeller

data class AkselDekkOgFelg(
    val akselId: Int,
    val belastningskodeDekk: String,
    val dekkdimensjon: String,
    val felgdimensjon: String,
    val hastighetskodeDekk: String,
    val innpress: String,
    val tvilling: Boolean
)