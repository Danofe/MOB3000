package com.example.mob3000oblig.DataApi

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("SVV-Authorization", "d4d24e5f-6509-4169-94ec-ad28ef0ba30f")
            .build()
        return chain.proceed(request)
    }

}