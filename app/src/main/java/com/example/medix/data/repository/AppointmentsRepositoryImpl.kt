package com.example.medix.data.repository

import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.AppointmentDeleteResponse
import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.domain.model.AppointmentResponse
import com.example.medix.domain.model.DoctorAppointmentResponse
import com.example.medix.domain.model.PatientAppointmentsResponse
import com.example.medix.domain.repository.AppointmentsRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class AppointmentsRepositoryImpl @Inject constructor(
    private val medixApi: MedixApi
) : AppointmentsRepository {

    override suspend fun createAppointment(appointmentRequest: AppointmentRequest) : AppointmentResponse {
        val doctorId = appointmentRequest.doctorId.toString().toRequestBody("text/plain".toMediaType())
        val patientId = appointmentRequest.patientId.toString().toRequestBody("text/plain".toMediaType())
        val year = appointmentRequest.year.toString().toRequestBody("text/plain".toMediaType())
        val month = appointmentRequest.month.toString().toRequestBody("text/plain".toMediaType())
        val day = appointmentRequest.day.toString().toRequestBody("text/plain".toMediaType())
        val hour = appointmentRequest.hour.toString().toRequestBody("text/plain".toMediaType())
        val minute = appointmentRequest.minute.toString().toRequestBody("text/plain".toMediaType())

        return medixApi.createAppointment(doctorId, patientId, year, month, day, hour, minute)
    }

    override suspend fun getPatientAppointments(patientId: Int): List<PatientAppointmentsResponse> {
        return medixApi.getPatientAppointments(patientId)
    }

    override suspend fun getDoctorAppointments(doctorId: Int): List<DoctorAppointmentResponse> {
        return medixApi.getDoctorAppointments(doctorId)
    }

    override suspend fun deleteAppointment(appointmentId: Int): AppointmentDeleteResponse {
        return medixApi.deleteAppointment(appointmentId)
    }
}