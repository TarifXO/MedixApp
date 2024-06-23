package com.example.medix.domain.useCases.appointments

data class AppointmentsUseCases(
    val createAppointmentUseCase : CreateAppointmentUseCase,
    val patientAppointmentsUseCase : PatientAppointmentsUseCase,
    val doctorAppointmentsUseCase : DoctorAppointmentsUseCase,
    val deleteAppointmentUseCase : DeleteAppointmentUseCase
)
