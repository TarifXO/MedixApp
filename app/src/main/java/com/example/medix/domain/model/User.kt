package com.example.medix.domain.model

data class User(
    val name: String,
    val email: String,
    val password: String,
    val isPatient: Boolean,
    val isDoctor: Boolean,
    val phone: String?,
    val dateOfBirth: String?,
    val gender: Gender?,
    val speciality: String?,
    val bio: String?,
    val address: String?,
    val wage: Double?,
    val image: String
)

data class SignUpParams(
    val name: String,
    val email: String,
    val password: String,
    val isPatient: Boolean,
    val isDoctor: Boolean,
)

data class LogInParams(
    val email: String,
    val password: String
)