package com.example.medix.domain.repository

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.User

/*interface AuthRepository {
    val currentUser: User?
    suspend fun login(
        email: String,
        password: String
    ) : Resource<User>
    suspend fun signup(
        name: String,
        email: String,
        password: String,
        isDoctor: Boolean,
        isPatient: Boolean
    ) : Resource<User>
    fun logout()

    suspend fun getUserData(): User?
}*/

/*interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<String>
    suspend fun signup(name: String, email: String, password: String, isDoctor: Boolean, isPatient: Boolean): Resource<String>
    suspend fun getUserData(accessToken: String): Resource<User>
    fun logout()
}*/