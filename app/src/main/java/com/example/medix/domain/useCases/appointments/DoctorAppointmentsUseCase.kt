package com.example.medix.domain.useCases.appointments

import com.example.medix.domain.model.DoctorAppointmentResponse
import com.example.medix.domain.repository.AppointmentsRepository

class DoctorAppointmentsUseCase(
    private val appointmentsRepository: AppointmentsRepository
) {
    suspend operator fun invoke(doctorId: Int) : List<DoctorAppointmentResponse> {
            return appointmentsRepository.getDoctorAppointments(doctorId)
    }
}