package com.example.medix.presentation.view.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.User
import com.example.medix.domain.useCases.auth.MedixUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val medixUseCases: MedixUseCases
) : ViewModel() {

    private val _userData = MutableStateFlow<Resource<User>?>(null)
    val userData: StateFlow<Resource<User>?> = _userData

    private val _loginFlow = MutableStateFlow<Resource<User>?>(null)
    val loginFlow : StateFlow<Resource<User>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<User>?>(null)
    val signupFlow : StateFlow<Resource<User>?> = _signupFlow

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = medixUseCases.logInUseCase(email, password)
        when (result) {
            is Resource.Success -> {
                _userData.value = Resource.Loading
                _userData.value = getUserData()
            }
            is Resource.Failure -> {
                // Handle failure case
                // For example, you might display an error message to the user
            }
            is Resource.Loading -> {
                // Handle loading state if necessary
            }
        }
        _loginFlow.value = result
    }

    fun signup(
        name: String, email: String, password: String, isDoctor: Boolean, isPatient: Boolean
    ) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = medixUseCases.signUpUseCase(name, email, password, isDoctor, isPatient)
        when (result) {
            is Resource.Success -> {
                _userData.value = Resource.Loading
                _userData.value = getUserData()
            }
            is Resource.Failure -> {
                // Handle failure case
                // For example, you might display an error message to the user
            }
            is Resource.Loading -> {
                // Handle loading state if necessary
            }
        }
        _signupFlow.value = result
    }

    fun logout() = viewModelScope.launch {
        _userData.value = null
        _loginFlow.value = null
        _signupFlow.value = null
        medixUseCases.logOutUseCase()
    }

    private suspend fun getUserData(): Resource<User> {
        return medixUseCases.getUserDataUseCase()
    }
}