package com.example.authentication_server_hw17.data.login

import com.example.authentication_server_hw17.data.ApiResponseDto
import com.example.authentication_server_hw17.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun login(@Body user: User): Response<ApiResponseDto>
}