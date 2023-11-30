package com.example.mob3000oblig.DataApi.DataModeller

data class Godkjenning(
    val forstegangsGodkjenning: com.example.mob3000oblig.DataApi.DataModeller.ForstegangsGodkjenning,
    val kjoretoymerknad: List<com.example.mob3000oblig.DataApi.DataModeller.Kjoretoymerknad>,
    val registreringsbegrensninger: com.example.mob3000oblig.DataApi.DataModeller.Registreringsbegrensninger,
    val tekniskGodkjenning: com.example.mob3000oblig.DataApi.DataModeller.TekniskGodkjenning,
    val tilleggsgodkjenninger: List<Any>
)