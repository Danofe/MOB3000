package com.example.mob3000oblig

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000oblig.Database.Firestore
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase

class FavoritterViewModel : ViewModel() {
    val favoritter = mutableStateOf<List<String>>(emptyList())
    val brukerID = Firebase.auth.currentUser?.uid.toString()

    init {
        hentFavoritter()
    }
    private fun hentFavoritter() {
        val db = Firebase.firestore

        db.collection("favoritter")
            .whereEqualTo("brukerID", brukerID)
            .get()
            .addOnSuccessListener { result ->
                val favoritterListe = mutableListOf<String>()
                for (document in result) {
                    favoritterListe.add(document.data["skilt"].toString())
                }
                favoritter.value = favoritterListe
            }
            .addOnFailureListener { exception ->
                Log.w("Feil", "Error getting documents: ", exception)
            }
    }
}