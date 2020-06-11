package com.ijikod.sensyne.Service

import com.ijikod.sensyne.SERVER_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ApiClient {

    private val retrofit: Retrofit

    init {

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS) // You can remove timeouts.
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(okHttpClient)
            .build()

    }

    fun getService() : Api = retrofit.create(
        Api::class.java)
}