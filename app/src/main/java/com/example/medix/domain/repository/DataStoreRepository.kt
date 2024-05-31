package com.example.medix.domain.repository

interface DataStoreRepository {
    suspend fun saveUserEmail(email: String)
    suspend fun getUserEmail(): String?
    suspend fun clearUserEmail()
    suspend fun saveUserId(userId: Int)
    suspend fun getUserId(): Int?
    suspend fun clearUserId()
}