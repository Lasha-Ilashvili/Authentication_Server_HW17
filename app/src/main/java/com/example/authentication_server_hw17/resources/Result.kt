package com.example.authentication_server_hw17.resources

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}
