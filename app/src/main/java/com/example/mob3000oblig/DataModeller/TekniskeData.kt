package com.example.mob3000oblig.DataModeller

data class TekniskeData(
    val akslinger: Akslinger,
    val bremser: Bremser,
    val dekkOgFelg: DekkOgFelg,
    val dimensjoner: Dimensjoner,
    val generelt: Generelt,
    val karosseriOgLasteplan: KarosseriOgLasteplan,
    val miljodata: Miljodata,
    val motorOgDrivverk: MotorOgDrivverk,
    val ovrigeTekniskeData: List<Any>,
    val persontall: Persontall,
    val tilhengerkopling: Tilhengerkopling,
    val vekter: Vekter
)