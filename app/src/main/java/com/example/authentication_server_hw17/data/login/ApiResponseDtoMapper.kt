package com.example.authentication_server_hw17.data.login

import com.example.authentication_server_hw17.data.ApiResponseDto
import com.example.authentication_server_hw17.domain.ApiResponse

fun ApiResponseDto.toDomain() = ApiResponse(token)