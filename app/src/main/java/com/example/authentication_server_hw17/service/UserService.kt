package com.example.authentication_server_hw17.service

import com.example.authentication_server_hw17.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/users/{email}")
    suspend fun getUser(@Path("email") email: String): Response<User>
}