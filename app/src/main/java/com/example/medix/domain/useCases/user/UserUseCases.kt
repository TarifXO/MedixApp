package com.example.medix.domain.useCases.user

data class UserUseCases(
    val logInUseCase: LogInUseCase,
    val registerUseCase: RegisterUseCase,
    val updatePatientUseCase: UpdatePatientUseCase,
    val updateDoctorUseCase: UpdateDoctorUseCase,
)
