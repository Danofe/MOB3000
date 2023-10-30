package com.example.mob3000oblig.DataApi

import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface DataInterface {

        @GET
        fun getKjoretoyDataListe(@Url url: String): Call<KjoretoyDataListe>


}