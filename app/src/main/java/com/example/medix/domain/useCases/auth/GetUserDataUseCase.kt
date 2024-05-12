package com.example.medix.domain.useCases.auth

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.User
import com.example.medix.domain.repository.AuthRepository

class GetUserDataUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(): Resource<User> {
        return try {
            val userData = authRepository.getUserData()
            if (userData != null) {
                Resource.Success(userData)
            } else {
                Resource.Failure(Exception("User data not found"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}