package com.example.mob3000oblig.DataModeller

data class Godkjenning(
    val forstegangsGodkjenning: ForstegangsGodkjenning,
    val kjoretoymerknad: List<Kjoretoymerknad>,
    val registreringsbegrensninger: Registreringsbegrensninger,
    val tekniskGodkjenning: TekniskGodkjenning,
    val tilleggsgodkjenninger: List<Any>
)