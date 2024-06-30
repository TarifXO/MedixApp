package com.example.medix

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.domain.repository.DataStoreRepository
import com.example.medix.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
    var startDestination by mutableStateOf(Screens.AuthRoute.route)
        private set

    init {
        viewModelScope.launch {
            val patientEmail = dataStoreRepository.getUserEmail()
            val patientId = dataStoreRepository.getUserId()
            val doctorEmail = dataStoreRepository.getDoctorEmail()
            val doctorId = dataStoreRepository.getDoctorId()
            Log.d("MainViewModel", "Retrieved user ID: $patientId, Email: $patientEmail")

            startDestination = when {
                !patientEmail.isNullOrEmpty() && patientId != 0 -> Screens.MedixNavigation.route
                !doctorEmail.isNullOrEmpty() && doctorId != 0 -> Screens.DoctorNavigation.route
                else -> Screens.AuthRoute.route
            }

            delay(300)
            splashCondition = false
        }
    }
}