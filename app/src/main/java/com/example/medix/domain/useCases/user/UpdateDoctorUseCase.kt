package com.example.medix.domain.useCases.user

import com.example.medix.domain.model.DoctorUpdateRequest
import com.example.medix.domain.repository.UserRepository
import java.io.File

class UpdateDoctorUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(id: Int, updateRequest: DoctorUpdateRequest) {
        userRepository.updateDoctor(id, updateRequest)
    }
}