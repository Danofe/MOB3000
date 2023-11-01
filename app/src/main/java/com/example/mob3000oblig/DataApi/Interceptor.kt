package com.example.mob3000oblig.DataApi

import com.example.mob3000oblig.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
      .newBuilder()
      .addHeader(
        "Content-Type",
        "application/json"
      )
      .addHeader(
        "SVV-Authorization",
        BuildConfig.API_KEY
      )
      .build()
    return chain.proceed(request)
  }
}