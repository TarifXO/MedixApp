package com.example.medix.domain.useCases.doctors

import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class SearchDoctorsUseCase(
    private val doctorsRepository: DoctorsRepository
) {
    suspend operator fun invoke(name: String) : List<Doctor> {
        return doctorsRepository.searchDoctorsByName(name)
    }
}