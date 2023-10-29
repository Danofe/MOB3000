package com.example.mob3000oblig.DataModeller

data class KarosseriOgLasteplan(
    val antallDorer: List<Int>,
    val dorUtforming: List<Any>,
    val karosseritype: Karosseritype,
    val kjennemerkestorrelseBak: KjennemerkestorrelseBak,
    val kjennemerkestorrelseForan: KjennemerkestorrelseForan,
    val kjennemerketypeBak: KjennemerketypeBak,
    val kjennemerketypeForan: KjennemerketypeForan,
    val kjoringSide: String,
    val oppbygningUnderstellsnummer: String,
    val plasseringAvDorer: PlasseringAvDorer,
    val plasseringFabrikasjonsplate: List<PlasseringFabrikasjonsplate>,
    val plasseringUnderstellsnummer: List<PlasseringUnderstellsnummer>,
    val rFarge: List<RFarge>
)