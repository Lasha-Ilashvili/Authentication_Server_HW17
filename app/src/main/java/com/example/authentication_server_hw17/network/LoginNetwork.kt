package com.example.authentication_server_hw17.network

import com.example.authentication_server_hw17.service.LoginService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object LoginNetwork {
    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    fun loginService(): LoginService = retrofit.create(LoginService::class.java)
}