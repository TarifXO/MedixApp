package com.example.medix.data.repository

import com.example.medix.data.authentication.Resource
import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.DoctorLoginResponse
import com.example.medix.domain.model.DoctorUpdateRequest
import com.example.medix.domain.model.DoctorUpdateResponse
import com.example.medix.domain.model.ForgotPasswordResponse
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.PatientLoginResponse
import com.example.medix.domain.model.PatientUpdateRequest
import com.example.medix.domain.model.PatientUpdateResponse
import com.example.medix.domain.model.RegisterRequest
import com.example.medix.domain.model.ResetPasswordResponse
import com.example.medix.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val medixApi: MedixApi
) : UserRepository {

    override suspend fun patientLogin(loginRequest: LogInRequest) : PatientLoginResponse {
        val email = loginRequest.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val password = loginRequest.password.toRequestBody("text/plain".toMediaTypeOrNull())

        return medixApi.patientLogIn(
            email = email,
            password = password
        )
    }

    override suspend fun doctorLogin(loginRequest: LogInRequest): DoctorLoginResponse {
        val email = loginRequest.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val password = loginRequest.password.toRequestBody("text/plain".toMediaTypeOrNull())

        return medixApi.doctorLogin(
            email = email,
            password = password
        )
    }

    override suspend fun forgotPassword(email: String): ForgotPasswordResponse {
        val email = email.toRequestBody("text/plain".toMediaTypeOrNull())

        return medixApi.forgotPassword(
            email = email
        )
    }

    override suspend fun resetPassword(
        password: String,
        confirmPassword: String,
        email: String,
        token: String
    ) : ResetPasswordResponse {
        val password = password.toRequestBody("text/plain".toMediaTypeOrNull())
        val confirmPassword = confirmPassword.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val token = token.toRequestBody("text/plain".toMediaTypeOrNull())

        return medixApi.resetPassword(password, confirmPassword, email, token)
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        email: String
    ): ResetPasswordResponse {
        val oldPassword = oldPassword.toRequestBody("text/plain".toMediaTypeOrNull())
        val newPassword = newPassword.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = email.toRequestBody("text/plain".toMediaTypeOrNull())

        return medixApi.changePassword(oldPassword, newPassword, email)
    }

    override suspend fun registerUser(registerRequest: RegisterRequest) {
        val username = registerRequest.username.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = registerRequest.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val password = registerRequest.password.toRequestBody("text/plain".toMediaTypeOrNull())
        val phone = registerRequest.phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val dateOfBirth = registerRequest.dateOfBirth.toRequestBody("text/plain".toMediaTypeOrNull())
        val gender = registerRequest.gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val isPatient = registerRequest.isPatient.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val isDoctor = registerRequest.isDoctor.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val image = registerRequest.image?.let {
            MultipartBody.Part.createFormData("Image", it.name, it.asRequestBody("Image/*".toMediaTypeOrNull()))
        }
        val speciality = registerRequest.speciality?.toRequestBody("text/plain".toMediaTypeOrNull())
        val bio = registerRequest.bio?.toRequestBody("text/plain".toMediaTypeOrNull())
        val address = registerRequest.address?.toRequestBody("text/plain".toMediaTypeOrNull())
        val wage = registerRequest.wage?.toString()?.toRequestBody("text/plain".toMediaTypeOrNull())

        medixApi.register(
            username = username,
            email = email,
            password = password,
            isPatient = isPatient,
            isDoctor = isDoctor,
            phone = phone,
            dateOfBirth = dateOfBirth,
            gender = gender,
            image = image,
            speciality = speciality,
            bio = bio,
            address = address,
            wage = wage
        )
    }

    override suspend fun updateDoctor(
        id: Int,
        updateRequest: DoctorUpdateRequest,
    ) : DoctorUpdateResponse {
        val name = updateRequest.name.toRequestBody("text/plain".toMediaTypeOrNull())
        val phone = updateRequest.phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = updateRequest.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val dateOfBirth = updateRequest.dateOfBirth.toRequestBody("text/plain".toMediaTypeOrNull())
        val gender = updateRequest.gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val speciality = updateRequest.speciality.toRequestBody("text/plain".toMediaTypeOrNull())
        val bio = updateRequest.bio.toRequestBody("text/plain".toMediaTypeOrNull())
        val address = updateRequest.address.toRequestBody("text/plain".toMediaTypeOrNull())
        val wage = updateRequest.wage.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val image = if (updateRequest.image.isNotEmpty()) {
            val file = File(updateRequest.image)
            MultipartBody.Part.createFormData("Image", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
        } else null

        return medixApi.updateDoctor(
            id = id,
            name = name,
            phone = phone,
            email = email,
            dateOfBirth = dateOfBirth,
            gender = gender,
            speciality = speciality,
            bio = bio,
            address = address,
            wage = wage,
            image = image
        )
    }

    override suspend fun updatePatient(
        id: Int,
        updateRequest: PatientUpdateRequest,
    ): PatientUpdateResponse {
        val name = updateRequest.name.toRequestBody("text/plain".toMediaTypeOrNull())
        val phone = updateRequest.phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = updateRequest.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val dateOfBirth = updateRequest.dateOfBirth.toRequestBody("text/plain".toMediaTypeOrNull())
        val gender = updateRequest.gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val image = if (updateRequest.image.isNotEmpty()) {
            val file = File(updateRequest.image)
            MultipartBody.Part.createFormData("Image", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
        } else null

        return medixApi.updatePatient(
            id = id,
            name = name,
            phone = phone,
            email = email,
            dateOfBirth = dateOfBirth,
            gender = gender,
            image = image
        )
    }
}