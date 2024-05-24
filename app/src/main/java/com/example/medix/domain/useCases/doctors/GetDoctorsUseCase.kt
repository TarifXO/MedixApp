package com.example.medix.domain.useCases.doctors

import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow

class GetDoctorsUseCase(
    private val doctorsRepository: DoctorsRepository
) {
    operator fun invoke(): Flow<PagingData<Doctor>> {
        return doctorsRepository.getDoctors()
    }
}