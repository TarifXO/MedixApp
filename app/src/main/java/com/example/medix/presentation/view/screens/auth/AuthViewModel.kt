package com.example.medix.presentation.view.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.LogInRequest
import com.example.medix.domain.model.LoginResponse
import com.example.medix.domain.model.Profile
import com.example.medix.domain.model.RegisterRequest
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
    private val _loginFlow = MutableStateFlow<Resource<Unit>?>(null)
    val loginFlow: StateFlow<Resource<Unit>?> = _loginFlow
    private val _forgotPasswordFlow = MutableStateFlow<Resource<Unit>?>(null)
    val forgotPasswordFlow: StateFlow<Resource<Unit>?> = _forgotPasswordFlow
    private val _resetPasswordFlow = MutableStateFlow<Resource<Unit>?>(null)
    val resetPasswordFlow: StateFlow<Resource<Unit>?> = _resetPasswordFlow

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

    fun login(loginRequest: LogInRequest) {
        viewModelScope.launch {
            _loginFlow.value = Resource.Loading
            try {
                val resource = userUseCases.logInUseCase.execute(loginRequest)
                handleResourceForLogin(resource, _loginFlow)
                if (resource is Resource.Success) {
                    val profile = resource.data.profile.firstOrNull()
                    if (profile != null) {
                        saveUserData(profile)
                    } else {
                        _loginFlow.value = Resource.Failure(Exception("Profile not found"))
                    }
                }
            } catch (e: Exception) {
                _loginFlow.value = Resource.Failure(e)
            }
        }
    }

    private suspend fun saveUserData(profile: Profile) {
        dataStoreRepository.saveUserId(profile.patient_id)
        dataStoreRepository.saveUserEmail(profile.patient_email)
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPasswordFlow.value = Resource.Loading
            try {
                val resource = userUseCases.forgotPasswordUseCase.execute(email)
                handleResource(resource, _forgotPasswordFlow)
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
                handleResource(resource, _resetPasswordFlow)
            } catch (e: Exception) {
                _resetPasswordFlow.value = Resource.Failure(e)
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

    private fun handleResourceForLogin(resource: Resource<LoginResponse>, flow: MutableStateFlow<Resource<Unit>?>) {
        when (resource) {
            is Resource.Success -> flow.value = Resource.Success(Unit)
            is Resource.Failure -> flow.value = Resource.Failure(resource.exception)
            is Resource.Loading -> flow.value = Resource.Loading
        }
    }
}