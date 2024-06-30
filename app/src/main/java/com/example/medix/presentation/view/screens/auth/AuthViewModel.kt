package com.example.medix.presentation.view.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.DoctorLoginResponse
import com.example.medix.domain.model.DoctorProfile
import com.example.medix.domain.model.ForgotPasswordResponse
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.PatientLoginResponse
import com.example.medix.domain.model.PatientProfile
import com.example.medix.domain.model.RegisterRequest
import com.example.medix.domain.model.ResetPasswordResponse
import com.example.medix.domain.repository.DataStoreRepository
import com.example.medix.domain.useCases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _signupFlow = MutableStateFlow<Resource<Unit>?>(null)
    val signupFlow: StateFlow<Resource<Unit>?> = _signupFlow
    private val _patientLoginFlow = MutableStateFlow<Resource<Unit>?>(null)
    val patientLoginFlow: StateFlow<Resource<Unit>?> = _patientLoginFlow
    private val _doctorLoginFlow = MutableStateFlow<Resource<Unit>?>(null)
    val doctorLoginFlow: StateFlow<Resource<Unit>?> = _doctorLoginFlow
    private val _forgotPasswordFlow = MutableStateFlow<Resource<Unit>?>(null)
    private val _resetPasswordFlow = MutableStateFlow<Resource<Unit>?>(null)
    private val _changePasswordFlow = MutableStateFlow<Resource<Unit>?>(null)
    val changePasswordFlow: StateFlow<Resource<Unit>?> = _changePasswordFlow

    fun signup(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _signupFlow.value = Resource.Loading
            try {
                val resource = userUseCases.registerUseCase.execute(registerRequest)
                handleResource(resource, _signupFlow)
                if (resource is Resource.Success) {
                    dataStoreRepository.saveUserEmail(registerRequest.email)
                }
            } catch (e: Exception) {
                _signupFlow.value = Resource.Failure(e)
            }
        }
    }

    fun patientLogin(loginRequest: LogInRequest) {
        viewModelScope.launch {
            _patientLoginFlow.value = Resource.Loading
            try {
                val resource = userUseCases.patientLogInUseCase.execute(loginRequest)
                handleResourceForPatientLogin(resource, _patientLoginFlow)
                if (resource is Resource.Success) {
                    val profile = resource.data.profile.firstOrNull()
                    if (profile != null) {
                        savePatientData(profile)
                    } else {
                        _patientLoginFlow.value = Resource.Failure(Exception("Profile not found"))
                    }
                }
            } catch (e: Exception) {
                _patientLoginFlow.value = Resource.Failure(e)
            }
        }
    }

    fun doctorLogin(loginRequest: LogInRequest) {
        viewModelScope.launch {
            _doctorLoginFlow.value = Resource.Loading
            try {
                val resource = userUseCases.doctorLogInUseCase.execute(loginRequest)
                handleResourceForDoctorLogin(resource, _doctorLoginFlow)
                if (resource is Resource.Success) {
                    val profile = resource.data.profile.firstOrNull()
                    if (profile != null) {
                        saveDoctorData(profile)
                    } else {
                        _doctorLoginFlow.value = Resource.Failure(Exception("Profile not found"))
                    }
                }
            } catch (e: Exception) {
                _doctorLoginFlow.value = Resource.Failure(e)
            }
        }
    }

    private suspend fun savePatientData(profile: PatientProfile) {
        dataStoreRepository.saveUserId(profile.patientId)
        dataStoreRepository.saveUserEmail(profile.patientEmail)
        Log.d("AuthViewModel", "User data saved: ID = ${profile.patientId}, Email = ${profile.patientEmail}")
    }

    private suspend fun saveDoctorData(profile: DoctorProfile) {
        dataStoreRepository.saveDoctorId(profile.doctorId)
        dataStoreRepository.saveDoctorEmail(profile.doctorEmail)
        Log.d("AuthViewModel", "User data saved: ID = ${profile.doctorId}, Email = ${profile.doctorEmail}")
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPasswordFlow.value = Resource.Loading
            try {
                val resource = userUseCases.forgotPasswordUseCase.execute(email)
                handleResourceForForgotPassword(resource, _forgotPasswordFlow)
            } catch (e: Exception) {
                _forgotPasswordFlow.value = Resource.Failure(e)
            }
        }
    }

    fun resetPassword(
        password: String,
        confirmPassword: String,
        email: String,
        token: String
    ) {
        viewModelScope.launch {
            _resetPasswordFlow.value = Resource.Loading
            try {
                val resource = userUseCases.resetPasswordUseCase.execute(password, confirmPassword, email, token)
                handleResourceForResetPassword(resource, _resetPasswordFlow)
            } catch (e: Exception) {
                _resetPasswordFlow.value = Resource.Failure(e)
            }
        }
    }

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        email: String
    ) {
        viewModelScope.launch {
            _changePasswordFlow.value = Resource.Loading
            try {
                val resource = userUseCases.changePasswordUseCase.execute(oldPassword, newPassword, email)
                handleResourceForResetPassword(resource, _changePasswordFlow)
            } catch (e: Exception) {
                _changePasswordFlow.value = Resource.Failure(e)
            }
        }
    }

    private fun handleResource(resource: Resource<Unit>, flow: MutableStateFlow<Resource<Unit>?>) {
        when (resource) {
            is Resource.Success -> flow.value = Resource.Success(Unit)
            is Resource.Failure -> flow.value = Resource.Failure(resource.exception)
            is Resource.Loading -> flow.value = Resource.Loading
        }
    }

    private fun handleResourceForPatientLogin(resource: Resource<PatientLoginResponse>, flow: MutableStateFlow<Resource<Unit>?>) {
        when (resource) {
            is Resource.Success -> flow.value = Resource.Success(Unit)
            is Resource.Failure -> flow.value = Resource.Failure(resource.exception)
            is Resource.Loading -> flow.value = Resource.Loading
        }
    }

    private fun handleResourceForDoctorLogin(resource: Resource<DoctorLoginResponse>, flow: MutableStateFlow<Resource<Unit>?>) {
        when (resource) {
            is Resource.Success -> flow.value = Resource.Success(Unit)
            is Resource.Failure -> flow.value = Resource.Failure(resource.exception)
            is Resource.Loading -> flow.value = Resource.Loading
        }
    }

    private fun handleResourceForForgotPassword(resource: Resource<ForgotPasswordResponse>, flow: MutableStateFlow<Resource<Unit>?>) {
        when (resource) {
            is Resource.Success -> flow.value = Resource.Success(Unit)
            is Resource.Failure -> flow.value = Resource.Failure(resource.exception)
            is Resource.Loading -> flow.value = Resource.Loading
        }
    }

    private fun handleResourceForResetPassword(resource: Resource<ResetPasswordResponse>, flow: MutableStateFlow<Resource<Unit>?>) {
        when (resource) {
            is Resource.Success -> flow.value = Resource.Success(Unit)
            is Resource.Failure -> flow.value = Resource.Failure(resource.exception)
            is Resource.Loading -> flow.value = Resource.Loading
        }
    }
}