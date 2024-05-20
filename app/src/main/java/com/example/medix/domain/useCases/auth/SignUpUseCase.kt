package com.example.medix.domain.useCases.auth

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.User

/*class SignUpUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(
        name: String, email: String, password: String, isDoctor: Boolean, isPatient: Boolean
    ): Resource<User> {
        return try {
            val result = authRepository.signup(name, email, password, isDoctor, isPatient)
            if (result is Resource.Success) {
                Resource.Success(result.data)
            } else {
                Resource.Failure(Exception("Failed to sign up"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}*/