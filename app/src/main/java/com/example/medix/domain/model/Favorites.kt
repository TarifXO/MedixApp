package com.example.medix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoritesRequest(
    val doctorId: Int,
    val patientId: Int
) : Parcelable

@Parcelize
data class FavoritesResponse(
    val id: Int,
    val doctorId: Int,
    val patientId: Int,
    val doctor: String,
    val patient: String
) : Parcelable

@Parcelize
data class FavoriteDoctorResponse(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val speciality: String,
    val bio: String,
    val address: String,
    val wage: Double,
    val image: String
) : Parcelable
