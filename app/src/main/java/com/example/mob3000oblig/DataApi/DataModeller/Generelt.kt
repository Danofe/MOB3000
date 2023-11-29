package com.example.mob3000oblig.DataApi.DataModeller

data class Generelt(
    val fabrikant: List<com.example.mob3000oblig.DataApi.DataModeller.Fabrikant>,
    val handelsbetegnelse: List<String>,
    val merke: List<com.example.mob3000oblig.DataApi.DataModeller.Merke>,
    val typebetegnelse: String
)