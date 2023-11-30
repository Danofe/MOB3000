package com.example.mob3000oblig.DataApi

import androidx.lifecycle.ViewModel

class APIViewModel() : ViewModel() {

  private val apiKobling = RetrofitInstance.api.create(DataInterface::class.java)
  suspend fun hentBilInfo(regNr: String): com.example.mob3000oblig.DataApi.DataModeller.KjoretoyDataListe? {
    val response = apiKobling.getKjoretoyDataListe(regNr)
    if (response.isSuccessful) {
      return response.body()
    }
    return null
  }
}