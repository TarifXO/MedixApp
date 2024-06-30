package com.example.medix.domain.repository

interface DataStoreRepository {
    suspend fun saveUserEmail(email: String)
    suspend fun saveDoctorEmail(email: String)
    suspend fun getUserEmail(): String?
    suspend fun getDoctorEmail(): String?
    suspend fun clearUserEmail()
    suspend fun clearDoctorEmail()
    suspend fun saveUserId(userId: Int)
    suspend fun saveDoctorId(userId: Int)
    suspend fun getUserId(): Int?
    suspend fun getDoctorId(): Int?
    suspend fun clearUserId()
    suspend fun clearDoctorId()
}