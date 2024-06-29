package com.example.medix.data.remote

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.AppointmentDeleteResponse
import com.example.medix.domain.model.AppointmentResponse
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.DoctorAppointmentResponse
import com.example.medix.domain.model.DoctorLoginResponse
import com.example.medix.domain.model.DoctorUpdateResponse
import com.example.medix.domain.model.DoctorUser
import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.domain.model.FavoritesResponse
import com.example.medix.domain.model.ForgotPasswordResponse
import com.example.medix.domain.model.PatientLoginResponse
import com.example.medix.domain.model.Patient
import com.example.medix.domain.model.PatientAppointmentsResponse
import com.example.medix.domain.model.PatientUpdateResponse
import com.example.medix.domain.model.ResetPasswordResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MedixApi {


    //Doctors
    @GET("/api/Doctors")
    suspend fun getDoctors(
    ): List<Doctor>

    @GET("/api/Doctors/{id}")
    fun getDoctorById(@Path("id") id: Int): Call<Doctor>

    @GET("/api/Doctors/{id}")
    fun getDoctorUserById(@Path("id") id: Int): Call<DoctorUser>

    @GET("/api/Doctors/SearchDoctor/{Name}")
    suspend fun searchDoctor(
        @Path("Name") name: String
    ): List<Doctor>

    @GET("/api/Doctors/GetDoctor/{specilization}")
    suspend fun getDoctorBySpeciality(
        @Path("specilization") specialization: String
    ): List<Doctor>

    @Multipart
    @PUT("/api/Doctors/{id}")
    suspend fun updateDoctor(
        @Path("id") id: Int,
        @Part("Name") name: RequestBody,
        @Part("Phone") phone: RequestBody,
        @Part("Email") email: RequestBody,
        @Part("Date_Of_Birth") dateOfBirth: RequestBody,
        @Part("Gender") gender: RequestBody,
        @Part("Speciality") speciality: RequestBody,
        @Part("bio") bio: RequestBody,
        @Part("Address") address: RequestBody,
        @Part("Wage") wage: RequestBody,
        @Part image: MultipartBody.Part?
    ): DoctorUpdateResponse


    //Authentication
    @Multipart
    @POST("/api/Authentication/login")
    suspend fun patientLogIn(
        @Part("Email") email: RequestBody,
        @Part("Password") password: RequestBody
    ) : PatientLoginResponse

    @Multipart
    @POST("/api/Authentication/login")
    suspend fun doctorLogin(
        @Part("Email") email: RequestBody,
        @Part("Password") password: RequestBody
    ): DoctorLoginResponse

    @Multipart
    @POST("/api/Authentication/ForgotPassword")
    suspend fun forgotPassword(
        @Part("email") email: RequestBody
    ): ForgotPasswordResponse

    @Multipart
    @POST("/api/Authentication/ResetPassword")
    suspend fun resetPassword(
        @Part("Password") password: RequestBody,
        @Part("ConfirmPassword") confirmPassword: RequestBody,
        @Part("Email") email: RequestBody,
        @Part("token") token: RequestBody
    ): ResetPasswordResponse

    @Multipart
    @PUT("/api/Authentication/ChangePassword")
    suspend fun changePassword(
        @Part("CurrentPassword") oldPassword: RequestBody,
        @Part("NewPassword") newPassword: RequestBody,
        @Part("Email") email: RequestBody
    ): ResetPasswordResponse

    @Multipart
    @POST("api/Authentication/Register")
    suspend fun register(
        @Part("Username") username: RequestBody,
        @Part("Email") email: RequestBody,
        @Part("Password") password: RequestBody,
        @Part("IsPatient") isPatient: RequestBody,
        @Part("IsDoctor") isDoctor: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("Date_Of_birth") dateOfBirth: RequestBody,
        @Part("Gender") gender: RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("Speciality") speciality: RequestBody?,
        @Part("bio") bio: RequestBody?,
        @Part("Address") address: RequestBody?,
        @Part("Wage") wage: RequestBody?
    )


    // Patients
    @GET("/api/Patients/{id}")
    fun getPatientById(@Path("id") id: Int): Call<Patient>

    @Multipart
    @PUT("api/Patients/{id}")
    suspend fun updatePatient(
        @Path("id") id: Int,
        @Part("Name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("Email") email: RequestBody,
        @Part("Date_Of_birth") dateOfBirth: RequestBody,
        @Part("Gender") gender: RequestBody,
        @Part image: MultipartBody.Part?
    ): PatientUpdateResponse


    //Appointments
    @Multipart
    @POST("/api/Appointments")
    suspend fun createAppointment(
        @Part("DoctorId") doctorId: RequestBody,
        @Part("PatientId") patientId: RequestBody,
        @Part("Year") year: RequestBody,
        @Part("Month") month: RequestBody,
        @Part("Day") day: RequestBody,
        @Part("Hour") hour: RequestBody,
        @Part("Minute") minute: RequestBody
    ): AppointmentResponse

    @GET("/api/Appointments/patient")
    suspend fun getPatientAppointments(
        @Query("patient_id") patientId: Int
    ) : List<PatientAppointmentsResponse>

    @GET("/api/Appointments/doctor")
    suspend fun getDoctorAppointments(
        @Query("doctor_id") doctorId: Int
    ): List<DoctorAppointmentResponse>

    @DELETE("/api/Appointments/delete")
    suspend fun deleteAppointment(
        @Query("appointment_id") appointmentId: Int
    ): AppointmentDeleteResponse


    //Favorites
    @Multipart
    @POST("/api/Favorites")
    suspend fun addFavorite(
        @Part("DoctorId") doctorId: RequestBody,
        @Part("PatientId") patientId: RequestBody
    ): FavoritesResponse

    @GET("/api/Favorites/{patient_id}")
    suspend fun getFavorites(
        @Path("patient_id") patientId: Int
    ): List<FavoriteDoctorResponse>

    @DELETE("/api/Favorites/{favoriteId}")
    suspend fun deleteFavorite(
        @Path("favoriteId") favoriteId: Int
    ): FavoritesResponse


}