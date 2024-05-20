package com.example.medix.data.remote.dto

import com.example.medix.domain.model.Doctor

data class DoctorsResponse(
    val doctors : List<Doctor>
)