package com.example.authentication_server_hw17.domain.login

import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import com.example.authentication_server_hw17.domain.ApiResponse
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    suspend fun login(user: User): Flow<ResultResponse<ApiResponse>>
}