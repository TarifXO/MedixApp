package com.example.medix.domain.useCases.appointments

import com.example.medix.domain.model.PatientAppointmentsResponse
import com.example.medix.domain.repository.AppointmentsRepository

class PatientAppointmentsUseCase(
    private val appointmentsRepository: AppointmentsRepository
) {
    suspend operator fun invoke(patientId: Int) : List<PatientAppointmentsResponse> {
            return appointmentsRepository.getPatientAppointments(patientId)
    }
}