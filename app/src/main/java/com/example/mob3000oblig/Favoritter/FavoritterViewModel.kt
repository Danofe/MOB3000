package com.example.mob3000oblig.Favoritter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritterViewModel : ViewModel() {
    val allefavoritter = mutableStateOf<List<String>>(emptyList())
    val favoritterSkilt = mutableStateOf<List<String>>(emptyList())
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
                val favoritterliste = mutableListOf<String>()
                val dataliste = mutableListOf<String>()
                for (document in result) {
                    favoritterliste.add(document.data["skilt"].toString())
                    dataliste.add(document.data.toString())
                }
                favoritterSkilt.value = favoritterliste
                allefavoritter.value = dataliste
            }
            .addOnFailureListener { exception ->
                Log.w("Feil", "Error getting documents: ", exception)
            }
    }
}