package com.example.authentication_server_hw17.service

import com.example.authentication_server_hw17.model.ApiResponse
import com.example.authentication_server_hw17.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun register(@Body user: User): Response<ApiResponse>
}