package com.example.authentication_server_hw17.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.data.datastore.DataStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _sessionFlow = MutableSharedFlow<SplashFragmentNavigationEvent>()
    val sessionFlow get() = _sessionFlow.asSharedFlow()

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            DataStore.readEmail().collect {
                _sessionFlow.emit(
                    if (it.isEmpty())
                        SplashFragmentNavigationEvent.NavigationLogin
                    else
                        SplashFragmentNavigationEvent.NavigationHome(it)
                )
            }
        }
    }
}

sealed class SplashFragmentNavigationEvent {
    object NavigationLogin : SplashFragmentNavigationEvent()
    data class NavigationHome(val email: String) : SplashFragmentNavigationEvent()
}