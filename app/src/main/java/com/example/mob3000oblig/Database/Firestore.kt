package com.example.mob3000oblig.Database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Log
import com.google.firebase.auth.ktx.auth

class Firestore {

    val db = Firebase.firestore
    fun leggInnFavoritt(name: String?, merke: String, type: String, farge: String,
                        girinfo: String, dristoffinfo: String, sitteplasser: String,
                        maksHastighet: String, hk: String, sistgodkjent: String, forstereg: String) {
        val brukerID = Firebase.auth.currentUser?.uid.toString()
        // lager nytt objekt av bil
        val bil = hashMapOf(
            "brukerID" to brukerID,
            "skilt" to name,
            "merke" to merke,
            "type" to type,
            "farge" to farge,
            "girinfo" to girinfo,
            "dristoffinfo" to dristoffinfo,
            "sitteplasser" to sitteplasser,
            "maksHastighet" to maksHastighet,
            "hestekrefter" to hk,
            "sistgodkjent" to sistgodkjent,
            "forstereg" to forstereg
        )
        // legger til bilen i collectionen "favoritter"
        db.collection("favoritter")
            .add(bil)
            .addOnSuccessListener { documentReference ->
                Log.d( "Databasesjekk","Dokument lagt til med ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("Databasesjekk","Feil ved lagring av dokument", e)
            }
    }
}