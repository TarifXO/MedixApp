package com.example.medix.data.remote

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.Doctor
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface MedixApi {


    //Doctors
    @GET("api/Doctors")
    suspend fun getDoctors(
    ): List<Doctor>

    @Multipart
    @GET("api/Doctors/SearchDoctor/Name")
    suspend fun searchDoctor(
        @Part("Name") name: RequestBody
    ): List<Doctor>


    //Authentication
    @Multipart
    @POST("/api/Authentication/login")
    suspend fun logIn(
        @Part("Email") email: RequestBody,
        @Part("Password") password: RequestBody
    )
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
    ): Resource<Unit>

    @Multipart
    @PUT("api/Doctors/{id}")
    suspend fun updateDoctor(
        @Path("id") id: Int,
        @Part("name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("dateOfBirth") dateOfBirth: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("speciality") speciality: RequestBody,
        @Part("bio") bio: RequestBody,
        @Part("address") address: RequestBody,
        @Part("wage") wage: RequestBody,
        @Part image: MultipartBody.Part?
    ): Resource<Unit>

    @Multipart
    @PUT("api/Patients/{id}")
    suspend fun updatePatient(
        @Path("id") id: Int,
        @Part("name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("dateOfBirth") dateOfBirth: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part image: MultipartBody.Part?
    ): Resource<Unit>
}