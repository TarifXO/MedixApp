package com.example.medix.presentation.view.screens.app.patient_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            dataStoreRepository.clearUserEmail()
            onLoggedOut()
        }
    }
}