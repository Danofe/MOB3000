package com.example.mob3000oblig.DataApi

import android.util.Log
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val client = OkHttpClient.Builder().apply {
    addInterceptor(Interceptor())}
    .build()

    val BASE_URL = "https://www.vegvesen.no/ws/no/vegvesen/kjoretoy/felles/datautlevering/enkeltoppslag/"
    val api = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(DataInterface::class.java)







