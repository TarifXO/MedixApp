package com.example.medix.domain.useCases.doctors

data class DoctorsUseCases(
    val getDoctors: GetDoctorsUseCase,
    val searchDoctors: SearchDoctorsUseCase,
    val getDoctorById: GetDoctorByIdUseCase,
    val getDoctorUserById: GetDoctorUserByIdUseCase,
    val getDoctorsBySpeciality: GetDoctorsBySpecialization
)
