package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.repository.UserRepository

class LogInUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(loginRequest: LogInRequest): Resource<Unit> {
        return try {
            userRepository.loginUser(loginRequest)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}