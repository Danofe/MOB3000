package com.example.mob3000oblig.DataApi

import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DataInterface {

        @GET("kjoretoydata?kjennemerke=DN26115")
        fun getKjoretoyDataListe(): Call<KjoretoyDataListe>


}