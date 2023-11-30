package com.example.mob3000oblig.DataApi

import androidx.lifecycle.ViewModel
import com.example.mob3000oblig.DataApi.DataModeller.KjoretoyDataListe

class APIViewModel() : ViewModel() {
    private val apiKobling = RetrofitInstance.api.create(DataInterface::class.java)
    suspend fun hentBilInfo(regNr:String): KjoretoyDataListe? {
      val response = apiKobling.getKjoretoyDataListe(regNr)
      if (response.isSuccessful) {
        return response.body()
      }
    return null
  }
}