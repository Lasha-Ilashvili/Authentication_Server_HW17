package com.example.authentication_server_hw17.data.login

import com.example.authentication_server_hw17.data.ApiResponseDto
import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import com.example.authentication_server_hw17.domain.ApiResponse
import com.example.authentication_server_hw17.domain.login.LogInRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(private val loginService: LoginService) :
    LogInRepository {
    override suspend fun login(user: User): Flow<ResultResponse<ApiResponse>> {
        return flow {
            try {
                val response = loginService.login(user)
                if (response.isSuccessful) {
                    val token = ApiResponseDto(user.email).toDomain()
                    emit(ResultResponse.Success(token))
                } else {
                    emit(ResultResponse.Error("Login failed"))
                }
            } catch (e: Exception) {
                emit(ResultResponse.Error("Network error"))
            }
        }
    }
}