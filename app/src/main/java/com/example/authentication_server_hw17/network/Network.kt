package com.example.authentication_server_hw17.network

import com.example.authentication_server_hw17.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    fun userService(): UserService = retrofit.create(UserService::class.java)
}