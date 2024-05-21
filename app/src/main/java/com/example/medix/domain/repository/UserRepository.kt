package com.example.medix.domain.repository

import com.example.medix.domain.model.DoctorUpdateRequest
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.PatientUpdateRequest
import com.example.medix.domain.model.RegisterRequest
import java.io.File

interface UserRepository{
    suspend fun loginUser(loginRequest: LogInRequest)
    suspend fun registerUser(registerRequest: RegisterRequest)
    suspend fun updateDoctor(id: Int, updateRequest: DoctorUpdateRequest)
    suspend fun updatePatient(id: Int, updateRequest: PatientUpdateRequest)
}