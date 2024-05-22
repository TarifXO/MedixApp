package com.example.medix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class LogInRequest(
    val email: String,
    val password: String
) : Parcelable

@Parcelize
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val isPatient: Boolean,
    val isDoctor: Boolean,
    val phone: String,
    val dateOfBirth: String,
    val gender: String,
    val speciality: String?,
    val bio: String?,
    val address: String?,
    val wage: Double?,
    val image: File?
) : Parcelable

@Parcelize
data class DoctorUpdateRequest(
    val name: String,
    val phone: String,
    val email: String,
    val dateOfBirth: String,
    val gender: String,
    val speciality: String,
    val bio: String,
    val address: String,
    val wage: Double,
    val image: File?
) : Parcelable

@Parcelize
data class PatientUpdateRequest(
    val name: String,
    val phone: String,
    val email: String,
    val dateOfBirth: String,
    val gender: String,
    val image: File?
) : Parcelable