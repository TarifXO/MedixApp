package com.example.medix.domain.useCases.doctors

import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository

class GetDoctorByIdUseCase(
    private val doctorsRepository: DoctorsRepository
) {
    suspend operator fun invoke(id: Int) : Doctor {
        return doctorsRepository.getDoctorById(id)
    }
}