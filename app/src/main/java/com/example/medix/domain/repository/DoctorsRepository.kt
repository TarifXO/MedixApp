package com.example.medix.domain.repository

import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import kotlinx.coroutines.flow.Flow

interface DoctorsRepository {
    fun getDoctors(doctors : List<String>): Flow<PagingData<Doctor>>
    //suspend fun getDoctorById(id: Int): Doctor
    //fun getDoctorsBySpecialization(specialization: String, doctors : List<String>): Flow<PagingData<Doctor>>
    //fun searchDoctorsByName(name: String, doctors : List<String>): Flow<PagingData<Doctor>>
    //suspend fun getAppointments(doctorsId: Int, patientId: Int, page: Int, pageSize: Int): List<Appointments>
    //suspend fun createAppointment(appointment: Appointments)
    //suspend fun deleteAppointment(appointmentId: Int)
    //suspend fun registerUser(user: User)
    //suspend fun loginUser(credentials: Credentials): AccessToken
    //suspend fun forgotPassword(email: String)
    //suspend fun resetPassword(resetInfo: ResetInfo)
   // suspend fun changePassword(newPassword: String)
    //suspend fun updateDoctor(id: Int, doctor: Doctor): Doctor

    //suspend fun getFavorites(patientId: Int): List<Favorite>
    //suspend fun addToFavorites(favorite: Favorite)
    //suspend fun removeFromFavorites(favoriteId: Int)
    //suspend fun getPatients(): List<Patient>
    //suspend fun getPatientById(id: Int): Patient
    //suspend fun updatePatient(id: Int, patient: Patient): Patient
}