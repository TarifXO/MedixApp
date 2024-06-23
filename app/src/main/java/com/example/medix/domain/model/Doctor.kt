package com.example.medix.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Doctor(
    val address: String?,
    val appointments: List<Appointment>? = null,
    val bio: String? = null,
    val dateOfBirth: String? = null,
    val email: String? = null,
    val favorites: List<Favorite>? = null,
    val gender: String? = null,
    val id: Int,
    val image: String? = null,
    val imagefile : String? = null,
    val name: String?,
    val phone: String? = null,
    val speciality: String? = null,
    val wage: Double? = null
) : Parcelable


@Parcelize
data class DoctorUser(
    val address: String?,
    val bio: String? = null,
    val dateOfBirth: String? = null,
    val email: String,
    val gender: String? = null,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String? = null,
    val speciality: String? = null,
    val wage: Double? = null,
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