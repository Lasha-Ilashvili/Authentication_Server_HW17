package com.example.authentication_server_hw17.presentation.register

import com.example.authentication_server_hw17.data.model.User

sealed class RegisterEvent {
    data class Register(val user: User) : RegisterEvent()
}