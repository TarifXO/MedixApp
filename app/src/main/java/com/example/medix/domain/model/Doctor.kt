package com.example.medix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val address: String?,
    val appointments: Int?,
    val bio: String?,
    val dateOfBirth: String?,
    val email: String?,
    val favorites: Int?,
    val gender: String?,
    val id: Int,
    val image: String?,
    val imagefile : String?,
    val name: String?,
    val phone: String?,
    val speciality: String?,
    val wage: Double?
) : Parcelable