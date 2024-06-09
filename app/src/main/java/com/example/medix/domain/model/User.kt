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
    val patientId: Int,
    val patientName: String,
    val patientPhone: String?,
    val patientEmail: String,
    val patientDateOfBirth: String?,
    val patientGender: String?,
    val patientImage: String?
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
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val dateOfBirth: String,
    val gender: String,
    val image: String
) : Parcelable

@Parcelize
data class PatientUpdateResponse(
    val id: Int,
    val name: String,
    val phone: String,
    val dateOfBirth: String,
    val gender: String,
    val imageUrl: String
) : Parcelable