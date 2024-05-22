package com.example.medix.domain.useCases.user

data class UserUseCases(
    val logInUseCase: LogInUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val registerUseCase: RegisterUseCase,
    val updatePatientUseCase: UpdatePatientUseCase,
    val updateDoctorUseCase: UpdateDoctorUseCase,
)
