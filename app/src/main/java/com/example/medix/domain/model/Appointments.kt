package com.example.medix.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class PatientAppointmentsResponse(
    @SerializedName("appointment_id") val appointmentId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("doctor_id") val doctorId: Int,
    @SerializedName("doctor_name") val doctorName: String,
    @SerializedName("doctor_phone") val doctorPhone: String,
    @SerializedName("doctor_email") val doctorEmail: String,
    @SerializedName("doctor_speciality") val doctorSpeciality: String?,
    @SerializedName("doctor_bio") val doctorBio: String,
    @SerializedName("doctor_address") val doctorAddress: String,
    @SerializedName("doctor_wage") val doctorWage: Int,
    @SerializedName("doctor_Image") val doctorImage: String
) : Parcelable

data class DoctorAppointmentResponse(
    @SerializedName("appointment_id") val appointmentId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("patient_name") val patientName: String,
    @SerializedName("patient_phone") val patientPhone: String?,
    @SerializedName("patient_email") val patientEmail: String,
    @SerializedName("patient_image") val patientImage: String
)

data class AppointmentDeleteResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("doctorId") val doctorId: Int,
    @SerializedName("patientId") val patientId: Int
)
