package com.example.medix.domain.useCases.auth

import com.example.medix.data.authentication.Resource

/*class LogOutUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Resource<Unit> {
        return try {
            authRepository.logout()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}*/