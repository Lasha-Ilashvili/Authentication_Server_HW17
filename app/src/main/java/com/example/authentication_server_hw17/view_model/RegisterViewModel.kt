package com.example.authentication_server_hw17.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.model.User
import com.example.authentication_server_hw17.network.RegisterNetwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class RegisterViewModel : ViewModel() {

    private val _registerResult = MutableStateFlow<User?>(null)
    val registerResult get() = _registerResult.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RegisterNetwork.registerService().register(User(email, password))

                if (response.isSuccessful) {
                    _registerResult.value = response.body()
//                    _registerResult.value = RegisterResult.Success(response.body()!!)

                } else {
//                    _registerResult.value = RegisterResult.Error("Register failed: ${response.errorBody()}")
                }
            } catch (e: IOException) {
//                _registerResult.value = RegisterResult.Error("Network error: ${e.message}")
            }
        }
    }
}