package com.example.medix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Patient(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val image: String,
    val dateOfBirth: String,
    val gender: String
) : Parcelable

enum class Gender {
    Male,
    Female
}