package com.example.authentication_server_hw17.domain.register

import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(user: User): Flow<ResultResponse<User>>
}