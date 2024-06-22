package com.example.medix.domain.repository

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.DoctorLoginResponse
import com.example.medix.domain.model.DoctorUpdateRequest
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.PatientLoginResponse
import com.example.medix.domain.model.PatientUpdateRequest
import com.example.medix.domain.model.PatientUpdateResponse
import com.example.medix.domain.model.RegisterRequest

interface UserRepository{
    suspend fun patientLogin(loginRequest: LogInRequest) : PatientLoginResponse
    suspend fun doctorLogin(loginRequest: LogInRequest) : DoctorLoginResponse
    suspend fun forgotPassword(email: String) : Resource<Unit>
    suspend fun resetPassword(password: String, confirmPassword: String, email: String, token : String) : Resource<Unit>
    suspend fun registerUser(registerRequest: RegisterRequest)
    suspend fun updateDoctor(id: Int, updateRequest: DoctorUpdateRequest)
    suspend fun updatePatient(id: Int, updateRequest: PatientUpdateRequest) : PatientUpdateResponse
}