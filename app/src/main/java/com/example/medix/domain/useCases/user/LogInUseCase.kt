package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.LoginResponse
import com.example.medix.domain.repository.UserRepository

class LogInUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(loginRequest: LogInRequest): Resource<LoginResponse> {
        return try {
            val loginResponse = userRepository.loginUser(loginRequest)
            Resource.Success(loginResponse)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}
