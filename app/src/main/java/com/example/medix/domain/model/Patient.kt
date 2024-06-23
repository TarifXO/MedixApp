package com.example.medix.domain.model

data class Patient(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val image: String,
    val dateOfBirth: String,
    val gender: String
)

enum class Gender {
    Male,
    Female
}