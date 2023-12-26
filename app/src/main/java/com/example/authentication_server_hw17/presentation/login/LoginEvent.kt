package com.example.authentication_server_hw17.presentation.login

import com.example.authentication_server_hw17.data.model.User

sealed class LoginEvent {
    data class Login(val user: User) : LoginEvent()
}