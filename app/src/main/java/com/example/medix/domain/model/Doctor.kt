package com.example.medix.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class DoctorDetails(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val speciality: String,
    val bio: String,
    val date_Of_Birth: String,
    val address: String,
    val wage: Int,
    val gender: String,
    val image: String
)

@Parcelize
data class Doctor(
    @SerializedName("doctor_address") val address: String?,
    @SerializedName("appointments") val appointments: List<Appointment>? = null,
    @SerializedName("doctor_bio") val bio: String? = null,
    @SerializedName("date_of_birth") val dateOfBirth: String? = null,
    @SerializedName("doctor_email") val email: String? = null,
    @SerializedName("favorites") val favorites: List<Favorite>? = null,
    @SerializedName("doctor_gender") val gender: String? = null,
    @SerializedName("doctor_id") val id: Int,
    @SerializedName("doctor_Image") val image: String? = null,
    @SerializedName("imagefile") val imagefile : String? = null,
    @SerializedName("doctor_name") val name: String?,
    @SerializedName("doctor_phone") val phone: String? = null,
    @SerializedName("doctor_speciality") val speciality: String? = null,
    @SerializedName("doctor_wage") val wage: Double? = null
) : Parcelable

@Parcelize
data class Appointment(
    val id: Int,
    val date: String,
    val time: String,
    val patientName: String,
    val patientPhone: String,
    val patientEmail: String,
) : Parcelable

@Parcelize
data class Favorite(
    val id: Int,
    val userId: Int,
    val doctorId: Int
) : Parcelable