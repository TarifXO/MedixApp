package com.example.medix.domain.useCases.doctors

import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow

class GetDoctorsBySpecialization(
    private val doctorsRepository: DoctorsRepository
) {
    suspend operator fun invoke(specialization: String) : List<Doctor> {
        return doctorsRepository.getDoctorsBySpeciality(specialization)
    }
}