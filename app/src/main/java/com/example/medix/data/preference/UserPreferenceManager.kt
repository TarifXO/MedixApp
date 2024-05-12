package com.example.medix.data.preference

/*import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferencesManager(context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    val isLoggedIn: Flow<Boolean>
        get() = dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }
}*/