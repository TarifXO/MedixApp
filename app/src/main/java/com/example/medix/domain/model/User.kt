package com.example.medix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File


@Parcelize
data class User(
    val id: Int?,
    val username: String?,
    val email: String?,
    val password: String?,
    val phone: String?,
    val dateOfBirth: String?,
    val gender: String?,
    val speciality: String?,
    val bio: String?,
    val address: String?,
    val wage: Double?,
    val image: File?,
    val isPatient: Boolean?,
    val isDoctor: Boolean?
) : Parcelable

@Parcelize
data class LogInRequest(
    val email: String,
    val password: String
) : Parcelable

@Parcelize
data class LoginResponse(
    val profile: List<Profile>
) : Parcelable

@Parcelize
data class Profile(
    val profileType: String,
    val patient_id: Int,
    val patient_name: String,
    val patient_phone: String?,
    val patient_email: String,
    val patient_date_of_birth: String?,
    val patien_gender: String?,
    val patient_image: String?
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