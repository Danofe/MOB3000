package com.example.mob3000oblig.Database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Log
import com.google.firebase.auth.ktx.auth

class Firestore {

    val db = Firebase.firestore
    fun leggInnFavoritt(name: String?, merke: String, type: String, farge: String,
                        girinfo: String, drivstoffinfo: String, sitteplasser: String,
                        maksHastighet: String, hk: String, sistgodkjent: String, forstereg: String) {

        val brukerID = Firebase.auth.currentUser?.uid.toString()

        val bil = hashMapOf(
            "brukerID" to brukerID,
            "skilt" to name,
            "merke" to merke,
            "type" to type,
            "farge" to farge,
            "girinfo" to girinfo,
            "drivstoffinfo" to drivstoffinfo,
            "sitteplasser" to sitteplasser,
            "maksHastighet" to maksHastighet,
            "hestekrefter" to hk,
            "sistgodkjent" to sistgodkjent,
            "forstereg" to forstereg
        )

        db.collection("favoritter")
            .whereEqualTo("brukerID", brukerID)
            .whereEqualTo("skilt", name)
            .get()
            .addOnSuccessListener { documentReference ->
                if (documentReference.isEmpty) {
                    db.collection("favoritter")
                        .add(bil)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Suksess", "bil lagt til i favoritter")
                        }
                        .addOnFailureListener { e ->
                            Log.w("Feil", "Fikk ikke lagt til bil", e)
                        }
                } else {
                    Log.d("Feil", "Bil finnes allerede")
                }
            }
            .addOnFailureListener { e ->
                Log.w("Feil", "Fikk ikke lagt til bil", e)
            }
    }
}