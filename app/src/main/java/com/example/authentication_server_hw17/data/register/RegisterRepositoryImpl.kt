package com.example.authentication_server_hw17.data.register

import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import com.example.authentication_server_hw17.domain.register.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerService: RegisterService) :
    RegisterRepository {
    override suspend fun register(user: User): Flow<ResultResponse<User>> {
        return flow {
            try {
                val response = registerService.register(user)
                if (response.isSuccessful) {
                    emit(ResultResponse.Success(user))
                } else {
                    emit(ResultResponse.Error("Registration failed"))
                }
            } catch (e: Exception) {
                emit(ResultResponse.Error("Network error"))
            }
        }
    }
}