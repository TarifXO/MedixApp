package com.example.medix.domain.useCases.appointments

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.domain.model.AppointmentResponse
import com.example.medix.domain.repository.AppointmentsRepository

class CreateAppointmentUseCase(
    private val appointmentsRepository: AppointmentsRepository
) {
    suspend operator fun invoke(appointmentRequest: AppointmentRequest): Resource<AppointmentResponse> {
        return try {
            val response = appointmentsRepository.createAppointment(appointmentRequest)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}