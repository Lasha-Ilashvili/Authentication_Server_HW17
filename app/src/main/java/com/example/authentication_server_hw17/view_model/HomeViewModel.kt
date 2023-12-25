package com.example.authentication_server_hw17.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_server_hw17.Events
import com.example.authentication_server_hw17.datastore.DataStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _sessionFlow = MutableSharedFlow<HomeFragmentNavigationEvent>()
    val sessionFlow get() = _sessionFlow.asSharedFlow()

    fun onEvent(event: Events) {
        if (event is Events.Logout) {
            logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            DataStore.saveEmail("")
            _sessionFlow.emit(HomeFragmentNavigationEvent.NavigationLogin)
        }
    }
}

sealed class HomeFragmentNavigationEvent {
    object NavigationLogin : HomeFragmentNavigationEvent()
}