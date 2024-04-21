package com.example.medix.domain.model

data class Doctor(
    val id: Int,
    val name: String,
    val description: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
