package com.example.medix.domain.model

data class Patient(
    val id: Int, // Unique identifier
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val image: String,
    val dateOfBirth: String,
    val gender: Gender
)

enum class Gender {
    Male,
    Female
}