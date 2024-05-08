package com.example.medix.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val userType: UserType
)

enum class UserType {
    PATIENT,
    DOCTOR
}