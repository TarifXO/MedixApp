package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.repository.UserRepository

class ForgotPasswordUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(email: String) : Resource<Unit> {
        return try {
            userRepository.forgotPassword(email)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}