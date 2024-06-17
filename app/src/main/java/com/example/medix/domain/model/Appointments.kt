package com.example.medix.domain.model

data class Appointments(
    val doctorId : Int,
    val patientId : Int,
    val date : String
)

data class AppointmentRequest(
    val doctorId : Int,
    val patientId : Int,
    val year : Int,
    val month : Int,
    val day : Int,
    val hour : Int,
    val minute : Int
)

data class AppointmentResponse(
    val id: Int,
    val date: Int,
    val time: Int,
    val doctorId: Int,
    val patientId: Int,
    val doctor: String,
    val patient: String
)
