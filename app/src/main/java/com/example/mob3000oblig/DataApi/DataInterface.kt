package com.example.mob3000oblig.DataApi

import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DataInterface {
  @GET
  suspend fun getKjoretoyDataListe(
    @Url
    url: String
  ): Response<KjoretoyDataListe>
}