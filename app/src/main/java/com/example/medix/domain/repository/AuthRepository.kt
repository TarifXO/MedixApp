package com.example.medix.domain.repository

import com.example.medix.data.authentication.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String) : Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String) : Resource<FirebaseUser>
    fun logout()
}