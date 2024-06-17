package com.example.medix.domain.repository

import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.domain.model.AppointmentResponse
import com.example.medix.domain.model.PatientAppointmentsResponse

interface AppointmentsRepository {
    suspend fun createAppointment(appointmentRequest: AppointmentRequest) : AppointmentResponse
    suspend fun getPatientAppointments(patientId: Int) : List<PatientAppointmentsResponse>
}