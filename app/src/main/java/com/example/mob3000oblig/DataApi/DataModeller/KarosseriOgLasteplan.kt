package com.example.mob3000oblig.DataApi.DataModeller

data class KarosseriOgLasteplan(
    val antallDorer: List<Int>,
    val dorUtforming: List<Any>,
    val karosseritype: com.example.mob3000oblig.DataApi.DataModeller.Karosseritype,
    val kjennemerkestorrelseBak: com.example.mob3000oblig.DataApi.DataModeller.KjennemerkestorrelseBak,
    val kjennemerkestorrelseForan: com.example.mob3000oblig.DataApi.DataModeller.KjennemerkestorrelseForan,
    val kjennemerketypeBak: com.example.mob3000oblig.DataApi.DataModeller.KjennemerketypeBak,
    val kjennemerketypeForan: com.example.mob3000oblig.DataApi.DataModeller.KjennemerketypeForan,
    val kjoringSide: String,
    val oppbygningUnderstellsnummer: String,
    val plasseringAvDorer: com.example.mob3000oblig.DataApi.DataModeller.PlasseringAvDorer,
    val plasseringFabrikasjonsplate: List<com.example.mob3000oblig.DataApi.DataModeller.PlasseringFabrikasjonsplate>,
    val plasseringUnderstellsnummer: List<com.example.mob3000oblig.DataApi.DataModeller.PlasseringUnderstellsnummer>,
    val rFarge: List<com.example.mob3000oblig.DataApi.DataModeller.RFarge>
)