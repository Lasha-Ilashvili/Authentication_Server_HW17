package com.example.authentication_server_hw17.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.model.User
import com.example.authentication_server_hw17.network.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class LoginViewModel : ViewModel() {

    private val _isEmailRegistered = MutableStateFlow<State?>(null)
    val isEmailRegistered get() = _isEmailRegistered.asStateFlow()

    fun checkEmail(email: String) {
        viewModelScope.launch {
            try {
                val response = Network.userService().getUser(email)
                val user = response.body()

                if (response.isSuccessful) {
                    _isEmailRegistered.value = State(user!!, true)
                } else {

                }
            } catch (e: IOException) {
                _isEmailRegistered.value = null
            }
        }
    }
}

data class State(
    val user: User,
    val isRegistered: Boolean
)