package com.example.medix.domain.useCases.doctors

import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import retrofit2.Call
import retrofit2.Response

class GetDoctorByIdUseCase(
    private val doctorsRepository: DoctorsRepository
) {
    operator fun invoke(id: Int) : Call<Doctor> {
        return doctorsRepository.getDoctorById(id)
    }
}