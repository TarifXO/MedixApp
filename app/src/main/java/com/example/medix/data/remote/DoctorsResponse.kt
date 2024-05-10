package com.example.medix.data.remote

import com.example.medix.domain.model.Doctor

data class DoctorsResponse(
    val doctors : List<Doctor>,
    val totalResults : Int
)