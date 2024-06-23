package com.example.medix.domain.useCases.doctors

import com.example.medix.domain.model.DoctorUser
import com.example.medix.domain.repository.DoctorsRepository
import retrofit2.Call

class GetDoctorUserByIdUseCase(
    private val doctorsRepository: DoctorsRepository
) {
    operator fun invoke(id: Int) : Call<DoctorUser> {
        return doctorsRepository.getDoctorUserById(id)
    }
}