package com.example.medix.domain.model

data class AccessToken(
    val token: String,
    val expiresIn: Long,
    val refreshToken: String?
)
