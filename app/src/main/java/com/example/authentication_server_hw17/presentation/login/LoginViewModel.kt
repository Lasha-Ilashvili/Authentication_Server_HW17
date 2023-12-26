package com.example.authentication_server_hw17.presentation.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import com.example.authentication_server_hw17.domain.ApiResponse
import com.example.authentication_server_hw17.domain.login.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val logInRepository: LogInRepository) :
    ViewModel() {

    private val _loginResult = MutableSharedFlow<ResultResponse<ApiResponse>>()
    val loginResult: SharedFlow<ResultResponse<ApiResponse>> = _loginResult

    fun onEvent(event: LoginEvent) {
        if (event is LoginEvent.Login) {
            login(event.user)
        }
    }

    private fun login(user: User) {
        viewModelScope.launch {
            logInRepository.login(user).collect {
                _loginResult.emit(
                    when (it) {
                        is ResultResponse.Error -> ResultResponse.Error(it.error)
                        is ResultResponse.Success -> ResultResponse.Success(it.token)
                    }
                )
            }
        }
    }
}