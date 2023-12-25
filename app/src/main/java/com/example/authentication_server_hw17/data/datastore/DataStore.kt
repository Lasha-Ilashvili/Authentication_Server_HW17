package com.example.authentication_server_hw17.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.authentication_server_hw17.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStore {

    private val EMAIL = stringPreferencesKey("email")

    suspend fun saveEmail(email: String) {
        App.application.applicationContext.dataStore.edit {
            it[EMAIL] = email
        }
    }

    fun readEmail(): Flow<String> = App.application.applicationContext.dataStore.data
        .map { preferences ->
            preferences[EMAIL] ?: ""
        }

}