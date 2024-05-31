package com.example.medix.domain.repository

import com.example.medix.domain.model.Patient
import retrofit2.Call

interface PatientsRepository {
    fun getPatientById(id: Int): Call<Patient>
}