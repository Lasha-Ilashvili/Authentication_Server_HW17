package com.example.authentication_server_hw17.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val email: String,
    val password: String
)
