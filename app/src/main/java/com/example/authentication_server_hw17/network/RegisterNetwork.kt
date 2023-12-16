package com.example.authentication_server_hw17.network

import com.example.authentication_server_hw17.service.RegisterService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RegisterNetwork {
    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    fun registerService(): RegisterService = retrofit.create(RegisterService::class.java)
}