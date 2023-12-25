package com.example.authentication_server_hw17

import com.example.authentication_server_hw17.model.User

sealed class Events {
    data class Login(val user: User) : Events()
    data class Register(val user: User) : Events()
    object Logout : Events()
}