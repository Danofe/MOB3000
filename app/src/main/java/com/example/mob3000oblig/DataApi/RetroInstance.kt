package com.example.mob3000oblig.DataApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val client = OkHttpClient.Builder().apply {
  addInterceptor(Interceptor())
}
  .build()
val BASE_URL =
  "https://www.vegvesen.no/ws/no/vegvesen/kjoretoy/felles/datautlevering/enkeltoppslag/"
val api = Retrofit.Builder()
  .baseUrl(BASE_URL)
  .client(client)
  .addConverterFactory(GsonConverterFactory.create())
  .build()

  val apiService = api.create(DataInterface::class.java)