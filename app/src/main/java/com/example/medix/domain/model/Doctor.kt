package com.example.medix.domain.model

import android.media.Image

data class Doctor(
    val id: Int, // Unique identifier
    val name: String,
    val email: String,
    val password: String,
    val gender: Gender,
    val speciality: String,
    val bio: String,
    val phoneNumber: String,
    val address: String,
    val dateOfBirth: String,
    val image: String,
    val wage: Double
)

enum class Gender { MALE, FEMALE}
