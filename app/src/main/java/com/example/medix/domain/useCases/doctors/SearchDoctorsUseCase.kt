package com.example.medix.domain.useCases.doctors

import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow

class SearchDoctorsUseCase(
    private val doctorsRepository: DoctorsRepository
) {
    operator fun invoke(name: String) : Flow<PagingData<Doctor>> {
        return doctorsRepository.searchDoctorsByName(name)
    }
}