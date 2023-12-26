package com.example.authentication_server_hw17.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import com.example.authentication_server_hw17.domain.register.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {

    private val _registrationResult = MutableSharedFlow<ResultResponse<User>>()
    val registrationResult: SharedFlow<ResultResponse<User>> = _registrationResult

    fun onEvent(event: RegisterEvent) {
        if (event is RegisterEvent.Register) {
            register(event.user)
        }
    }

    private fun register(user: User) {
        viewModelScope.launch {
            registerRepository.register(user).collect {
                _registrationResult.emit(
                    when (it) {
                        is ResultResponse.Error -> ResultResponse.Error(it.error)
                        is ResultResponse.Success -> ResultResponse.Success(it.token)
                    }
                )
            }
        }
    }
}