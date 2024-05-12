package com.example.medix.data.remote

import com.example.medix.domain.model.AccessToken
import com.example.medix.domain.model.Appointments
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.ResetInfo
import com.example.medix.domain.model.User
import okhttp3.Credentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MedixApi {

    @GET("api/Doctors")
    fun getDoctors(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): DoctorsResponse

    @GET("api/Doctors/{id}")
    suspend fun getDoctorById(
        @Path("id") id: Int
    ): Doctor

    @GET("api/Doctors/GetDoctor/{specialization}")
    suspend fun getDoctorsBySpecialization(
        @Path("specialization") specialization: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): DoctorsResponse

    @GET("api/Doctors/SearchDoctor/{name}")
    suspend fun searchDoctorsByName(
        @Path("name") name: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): DoctorsResponse

    ////////////////////

    @POST("api/Authentication/Register")
    suspend fun registerUser(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("isDoctor") isDoctor: Boolean,
        @Query("isPatient") isPatient: Boolean
    ) : AccessToken

    @POST("api/Authentication/Login")
    suspend fun loginUser(
        @Body email: String,
        @Body password: String
    ): AccessToken

    @POST("api/Authentication/ForgotPassword")
    suspend fun forgotPassword(@Body email: String)

    @POST("api/Authentication/ResetPassword")
    suspend fun resetPassword(@Body resetInfo: ResetInfo)

    @PUT("api/Authentication/ChangePassword")
    suspend fun changePassword(@Body newPassword: String)

    ////////////////////

    @GET("api/Appointments/{doctors_id}/{patient_id}")
    suspend fun getAppointments(
        @Path("doctors_id") doctorsId: Int,
        @Path("patient_id") patientId: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Appointments>

    @POST("api/Appointments")
    suspend fun createAppointment(@Body appointment: Appointments)

    @DELETE("api/Appointments/delete/{appointment_id}")
    suspend fun deleteAppointment(@Path("appointment_id") appointmentId: Int)
}