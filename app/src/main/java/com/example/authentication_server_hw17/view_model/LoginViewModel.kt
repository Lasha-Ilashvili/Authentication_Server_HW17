package com.example.authentication_server_hw17.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.datastore.DataStore
import com.example.authentication_server_hw17.model.ApiResponse
import com.example.authentication_server_hw17.model.User
import com.example.authentication_server_hw17.network.ApiClient.getLoginResult
import com.example.authentication_server_hw17.resources.ResultResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableSharedFlow<ResultResponse<ApiResponse>>()
    val loginResult: SharedFlow<ResultResponse<ApiResponse>> = _loginResult

    fun login(user: User) {
        viewModelScope.launch {
            try {
                val response = getLoginResult().login(user)
                if (response.isSuccessful) {
                    DataStore.saveEmail(user.email)
                    val token = ApiResponse(user.email)
                    _loginResult.emit(ResultResponse.Success(token))
                } else {
                    _loginResult.emit(ResultResponse.Error("Login failed"))
                }
            } catch (e: Exception) {
                _loginResult.emit(ResultResponse.Error("Network error"))
            }
        }
    }
}