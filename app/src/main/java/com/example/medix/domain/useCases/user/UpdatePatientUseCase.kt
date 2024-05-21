package com.example.medix.domain.useCases.user

import com.example.medix.domain.model.PatientUpdateRequest
import com.example.medix.domain.repository.UserRepository
import java.io.File

class UpdatePatientUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(id: Int, updateRequest: PatientUpdateRequest) {
        userRepository.updatePatient(id, updateRequest)
    }
}