package com.example.medix.domain.model

data class ResetInfo(
    val email: String,
    val resetCode: String,
    val newPassword: String
)
