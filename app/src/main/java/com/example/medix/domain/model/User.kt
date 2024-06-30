package com.example.medix.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
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
data class PatientLoginResponse(
    val profile: List<PatientProfile>
) : Parcelable

@Parcelize
data class PatientProfile(
    val profileType: String,
    @SerializedName("patient_id") val patientId: Int,
    @SerializedName("patient_name") val patientName: String,
    @SerializedName("patient_phone") val patientPhone: String?,
    @SerializedName("patient_email") val patientEmail: String,
    @SerializedName("patient_date_of_birth") val patientDateOfBirth: String?,
    @SerializedName("patien_gender") val patientGender: String?,
    @SerializedName("patient_image") val patientImage: String?
) : Parcelable

data class DoctorLoginResponse(
    val profile: List<DoctorProfile>
)

data class DoctorProfile(
    @SerializedName("profileType") val profileType: String,
    @SerializedName("doctor_id") val doctorId: Int,
    @SerializedName("doctor_name") val doctorName: String,
    @SerializedName("doctor_phone") val doctorPhone: String?,
    @SerializedName("doctor_email") val doctorEmail: String,
    @SerializedName("doctor_speciality") val doctorSpeciality: String?,
    @SerializedName("doctor_bio") val doctorBio: String?,
    @SerializedName("doctor_address") val doctorAddress: String?,
    @SerializedName("doctor_wage") val doctorWage: String?,
    @SerializedName("doctor_gender") val doctorGender: String?,
    @SerializedName("doctor_date_of_birth") val doctorDateOfBirth: String?,
    @SerializedName("doctor_Image") val doctorImage: String?
)


@Parcelize
data class ForgotPasswordResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
) : Parcelable


@Parcelize
data class ResetPasswordResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
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
    @SerializedName("id")val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("phone")val phone: String,
    @SerializedName("data_Of_Birth")val dateOfBirth: String,
    @SerializedName("gender")val gender: String,
    @SerializedName("imageUrl")val imageUrl: String
) : Parcelable

@Parcelize
data class DoctorUpdateRequest(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val dateOfBirth: String,
    val gender: String,
    val speciality: String,
    val bio: String,
    val address: String,
    val wage: Double,
    val image: String
) : Parcelable

data class DoctorUpdateResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("date_Of_Birth") val dateOfBirth: String,
    @SerializedName("speciality") val speciality: String,
    @SerializedName("bio") val bio: String,
    @SerializedName("address") val address: String,
    @SerializedName("wage") val wage: Double,
    @SerializedName("gender") val gender: String,
    @SerializedName("imageUrl") val imageUrl: String
)