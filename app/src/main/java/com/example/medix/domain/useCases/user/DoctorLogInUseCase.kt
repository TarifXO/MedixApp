package com.example.medix.domain.useCases.user

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.DoctorLoginResponse
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.PatientLoginResponse
import com.example.medix.domain.repository.UserRepository

class DoctorLogInUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(loginRequest: LogInRequest): Resource<DoctorLoginResponse> {
        return try {
            val loginResponse = userRepository.doctorLogin(loginRequest)
            Resource.Success(loginResponse)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}
