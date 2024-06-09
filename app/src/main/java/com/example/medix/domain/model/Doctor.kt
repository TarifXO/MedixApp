package com.example.medix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val address: String?,
    val appointments: List<Appointment>?,
    val bio: String?,
    val dateOfBirth: String?,
    val email: String?,
    val favorites: List<Favorite>?,
    val gender: String?,
    val id: Int,
    val image: String?,
    val imagefile : String?,
    val name: String?,
    val phone: String?,
    val speciality: String?,
    val wage: Double?
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