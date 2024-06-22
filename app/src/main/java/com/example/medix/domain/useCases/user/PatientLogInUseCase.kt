package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.PatientLoginResponse
import com.example.medix.domain.repository.UserRepository

class PatientLogInUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(loginRequest: LogInRequest): Resource<PatientLoginResponse> {
        return try {
            val loginResponse = userRepository.patientLogin(loginRequest)
            Resource.Success(loginResponse)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}
