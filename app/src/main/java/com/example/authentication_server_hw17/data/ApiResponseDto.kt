package com.example.authentication_server_hw17.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponseDto(
    val token: String
)