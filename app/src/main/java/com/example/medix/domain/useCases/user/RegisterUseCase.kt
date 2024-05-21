package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.RegisterRequest
import com.example.medix.domain.repository.UserRepository
import java.io.File

class RegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(registerRequest: RegisterRequest): Resource<Unit> {
        return try {
            userRepository.registerUser(registerRequest)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}