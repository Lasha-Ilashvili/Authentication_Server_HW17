package com.example.authentication_server_hw17.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.model.ApiResponse
import com.example.authentication_server_hw17.model.User
import com.example.authentication_server_hw17.network.ApiClient.getRegisterResult
import com.example.authentication_server_hw17.resources.ResultResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _registrationResult = MutableSharedFlow<ResultResponse<ApiResponse>>()
    val registrationResult: SharedFlow<ResultResponse<ApiResponse>> = _registrationResult

    fun register(user: User) {
        viewModelScope.launch {
            try {
                val response = getRegisterResult().register(user)
                if (response.isSuccessful) {
                    val token = ApiResponse("${user.email}|${user.password}")
                    _registrationResult.emit(ResultResponse.Success(token))
                } else {
                    _registrationResult.emit(ResultResponse.Error("Registration failed"))
                }
            } catch (e: Exception) {
                _registrationResult.emit(ResultResponse.Error("Network error"))
            }
        }
    }
}