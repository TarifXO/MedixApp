package com.example.medix.data.remote

import com.example.medix.domain.model.Doctor
import retrofit2.http.GET

interface MedixApi {

    @GET("api/Doctors")
    suspend fun getDoctors(
    ): List<Doctor>

}