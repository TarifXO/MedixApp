package com.example.medix.domain.repository

import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.domain.model.AppointmentResponse

interface AppointmentsRepository {
    suspend fun createAppointment(appointmentRequest: AppointmentRequest) : AppointmentResponse
}