package com.example.medix.data.repository

import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.Patient
import com.example.medix.domain.repository.PatientsRepository
import retrofit2.Call
import javax.inject.Inject

class PatientsRepositoryImpl @Inject constructor(
    private val medixApi: MedixApi
): PatientsRepository {
    override fun getPatientById(id: Int) : Call<Patient> {
        return medixApi.getPatientById(id)
    }
}