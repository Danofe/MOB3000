package com.example.mob3000oblig.Database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Log
import com.google.firebase.auth.ktx.auth

class Firestore {

    val db = Firebase.firestore
    fun leggInnFavoritt(name: String?, merke: String, hk: String, antSeter: String,
                        farge: String, type: String, toppHastighet: String, drivstoff: String, girtyp: String,
                        lengde: String, hoyde: String, bredde: String, vekt: String, hybrid: String,
                        sistgodkjent: String, nesteEU: String, forsteReg: String, antdorer: String,
                        handelsbetegnelse: String
    ) {

        val brukerID = Firebase.auth.currentUser?.uid.toString()

        val bil = hashMapOf(
            "brukerID" to brukerID,
            "skilt" to name,
            "merke" to merke,
            "type" to type,
            "farge" to farge,
            "girinfo" to girtyp,
            "drivstoffinfo" to drivstoff,
            "sitteplasser" to antSeter,
            "maksHastighet" to toppHastighet,
            "hestekrefter" to hk,
            "sistgodkjent" to sistgodkjent,
            "forstereg" to forsteReg,
            "hoyde" to hoyde,
            "bredde" to bredde,
            "lengde" to lengde,
            "vekt" to vekt,
            "hybrid" to hybrid,
            "nesteEU" to nesteEU,
            "antDorer" to antdorer,
            "serie" to handelsbetegnelse
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