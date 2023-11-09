package com.example.mob3000oblig.DataApi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import kotlinx.coroutines.launch
import retrofit2.Call

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