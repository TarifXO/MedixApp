package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.ResetPasswordResponse
import com.example.medix.domain.repository.UserRepository

class ChangePasswordUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(
        oldPassword: String,
        newPassword: String,
        email: String
    ) : Resource<ResetPasswordResponse> {
        return try {
            val response = userRepository.changePassword(oldPassword, newPassword, email)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}