package com.example.medix.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.medix.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("user_prefs")
private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
private val USER_ID_KEY = intPreferencesKey("user_id")
private val DOCTOR_EMAIL_KEY = stringPreferencesKey("doctor_email")
private val DOCTOR_ID_KEY = intPreferencesKey("doctor_id")

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    override suspend fun saveUserEmail(email: String) {
        Log.d("DataStoreRepositoryImpl", "Saving email: $email")
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    override suspend fun saveDoctorEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[DOCTOR_EMAIL_KEY] = email
        }
    }

    override suspend fun getUserEmail(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL_KEY]
        }.first().also { email ->
            Log.d("DataStoreRepositoryImpl", "Retrieved email: $email")
        }
    }

    override suspend fun getDoctorEmail(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[DOCTOR_EMAIL_KEY]
        }.first().also { email ->
            Log.d("DataStoreRepositoryImpl", "Retrieved email: $email")
        }
    }

    override suspend fun clearUserEmail() {
        Log.d("DataStoreRepositoryImpl", "Clearing email")
        context.dataStore.edit { preferences ->
            preferences.remove(USER_EMAIL_KEY)
        }
    }

    override suspend fun clearDoctorEmail() {
        context.dataStore.edit { preferences ->
            preferences.remove(DOCTOR_EMAIL_KEY)
        }
    }

    override suspend fun saveUserId(userId: Int) {
        Log.d("DataStoreRepositoryImpl", "Saving user ID: $userId")
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    override suspend fun saveDoctorId(userId: Int) {
        context.dataStore.edit { preferences ->
            preferences[DOCTOR_ID_KEY] = userId
        }
    }

    override suspend fun getUserId(): Int? {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }.first().also { userId ->
            Log.d("DataStoreRepositoryImpl", "Retrieved user ID: $userId")
        }
    }

    override suspend fun getDoctorId(): Int? {
        return context.dataStore.data.map { preferences ->
            preferences[DOCTOR_ID_KEY]
        }.first().also { userId ->
            Log.d("DataStoreRepositoryImpl", "Retrieved user ID: $userId")
        }
    }

    override suspend fun clearUserId() {
        Log.d("DataStoreRepositoryImpl", "Clearing user ID")
        context.dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }

    override suspend fun clearDoctorId() {
        context.dataStore.edit { preferences ->
            preferences.remove(DOCTOR_ID_KEY)
        }
    }
}
