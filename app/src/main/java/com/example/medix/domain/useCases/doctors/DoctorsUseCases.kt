package com.example.medix.domain.useCases.doctors

import com.example.medix.domain.repository.DoctorsRepository

data class DoctorsUseCases(
    val getDoctors: GetDoctorsUseCase,
    val searchDoctors: SearchDoctorsUseCase,
    val getDoctorById: GetDoctorByIdUseCase,
    val doctorsRepository: DoctorsRepository
)
