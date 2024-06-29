package com.example.medix.domain.useCases.user

data class UserUseCases(
    val patientLogInUseCase: PatientLogInUseCase,
    val doctorLogInUseCase: DoctorLogInUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val changePasswordUseCase: ChangePasswordUseCase,
    val registerUseCase: RegisterUseCase,
    val updatePatientUseCase: UpdatePatientUseCase,
    val updateDoctorUseCase: UpdateDoctorUseCase,
)
