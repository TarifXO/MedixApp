package com.example.medix.domain.repository

interface DataStoreRepository {
    suspend fun saveUserEmail(email: String)
    suspend fun getUserEmail(): String?
}