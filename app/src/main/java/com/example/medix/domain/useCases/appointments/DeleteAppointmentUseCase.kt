package com.example.medix.domain.useCases.appointments

import com.example.medix.domain.model.AppointmentDeleteResponse
import com.example.medix.domain.repository.AppointmentsRepository

class DeleteAppointmentUseCase(
    private val appointmentRepository: AppointmentsRepository
) {
    suspend operator fun invoke(appointmentId: Int) : AppointmentDeleteResponse{
        return appointmentRepository.deleteAppointment(appointmentId)
    }
}