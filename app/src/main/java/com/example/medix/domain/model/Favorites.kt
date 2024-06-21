package com.example.medix.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoritesRequest(
    val doctorId: Int,
    val patientId: Int
) : Parcelable

@Parcelize
data class FavoritesResponse(
    @SerializedName ("id") val id: Int,
    @SerializedName ("doctorId") val doctorId: Int,
    @SerializedName ("patientId") val patientId: Int,
    @SerializedName ("doctor") val doctor: String,
    @SerializedName ("patient") val patient: String
) : Parcelable

@Parcelize
data class FavoritesDeleteResponse(
    @SerializedName ("id") val id: Int,
    @SerializedName ("doctorId") val doctorId: Int,
    @SerializedName ("patientId") val patientId: Int,
    @SerializedName ("doctor") val doctor: String,
    @SerializedName ("patient") val patient: String
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
