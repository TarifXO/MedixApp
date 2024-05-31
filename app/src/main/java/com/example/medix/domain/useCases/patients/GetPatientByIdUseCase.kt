package com.example.medix.domain.useCases.patients

import com.example.medix.domain.model.Patient
import com.example.medix.domain.repository.PatientsRepository
import retrofit2.Call

class GetPatientByIdUseCase(
    private val patientsRepository: PatientsRepository
) {
    operator fun invoke(id: Int) : Call<Patient> {
        return patientsRepository.getPatientById(id)
    }
}