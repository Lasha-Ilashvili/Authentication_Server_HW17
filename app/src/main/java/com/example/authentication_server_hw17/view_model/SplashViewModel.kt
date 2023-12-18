package com.example.authentication_server_hw17.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.datastore.DataStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _sessionFlow = MutableSharedFlow<String>()
    val sessionFlow get() = _sessionFlow.asSharedFlow()

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            DataStore.readEmail().collect {
                _sessionFlow.emit(it)
            }
        }
    }
}