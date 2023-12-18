package com.example.authentication_server_hw17.network

import com.example.authentication_server_hw17.service.LoginService
import com.example.authentication_server_hw17.service.RegisterService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiClient {

    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun getRegisterResult(): RegisterService = retrofit.create(RegisterService::class.java)

    fun getLoginResult(): LoginService = retrofit.create(LoginService::class.java)
}