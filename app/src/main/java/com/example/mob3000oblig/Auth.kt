package com.example.mob3000oblig

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class Auth {
    val currentUser:FirebaseUser? = Firebase.auth.currentUser

    fun innLogget():Boolean{
        return currentUser != null
    }

    fun loggUt(){
        Firebase.auth.signOut()
    }

    fun hentBrukerId():String{
        return currentUser?.uid.toString()
    }

    suspend fun lagBruker(email:String, passord:String):Boolean{
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, passord).await()
            true
        }catch (e:Exception){
            false
        }
    }

    suspend fun login(email:String, passord:String):Boolean{
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, passord).await()
            true
        }catch (e:Exception){
            false
        }
    }



}