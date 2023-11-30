package com.example.mob3000oblig.Auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class Auth {
  val bruker: FirebaseUser? = Firebase.auth.currentUser

  fun innlogget(): Boolean {
    return bruker != null
  }

  fun loggUt() {
    Firebase.auth.signOut()
  }

  fun slettBruker() {
    bruker?.delete()
  }

  fun hentBrukerEmail(): String {
    return bruker?.email.toString()
  }

  fun byttPassord(passord: String) {
    bruker?.updatePassword(passord)
  }

  suspend fun lagBruker(email: String, passord: String): Boolean {
    return try {
      Firebase.auth.createUserWithEmailAndPassword(
        email,
        passord
      ).await()
      true
    } catch (e: Exception) {
      false
    }
  }

  suspend fun login(email: String, passord: String): Boolean {
    return try {
      Firebase.auth.signInWithEmailAndPassword(
        email,
        passord
      ).await()
      true
    } catch (e: Exception) {
      false
    }
  }
}