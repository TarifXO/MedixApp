package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.repository.UserRepository

class ResetPasswordUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(password: String, confirmPassword: String, email: String, token : String)
    : Resource<Unit> {
        return try {
            userRepository.resetPassword(password, confirmPassword, email, token)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}