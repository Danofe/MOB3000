package com.example.mob3000oblig.DataApi.DataModeller

data class TekniskeData(
    val akslinger: com.example.mob3000oblig.DataApi.DataModeller.Akslinger,
    val bremser: com.example.mob3000oblig.DataApi.DataModeller.Bremser,
    val dekkOgFelg: com.example.mob3000oblig.DataApi.DataModeller.DekkOgFelg,
    val dimensjoner: com.example.mob3000oblig.DataApi.DataModeller.Dimensjoner,
    val generelt: com.example.mob3000oblig.DataApi.DataModeller.Generelt,
    val karosseriOgLasteplan: com.example.mob3000oblig.DataApi.DataModeller.KarosseriOgLasteplan,
    val miljodata: com.example.mob3000oblig.DataApi.DataModeller.Miljodata,
    val motorOgDrivverk: com.example.mob3000oblig.DataApi.DataModeller.MotorOgDrivverk,
    val ovrigeTekniskeData: List<Any>,
    val persontall: com.example.mob3000oblig.DataApi.DataModeller.Persontall,
    val tilhengerkopling: com.example.mob3000oblig.DataApi.DataModeller.Tilhengerkopling,
    val vekter: com.example.mob3000oblig.DataApi.DataModeller.Vekter
)