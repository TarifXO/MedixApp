package com.example.medix.presentation.view.screens.app.doctor_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            dataStoreRepository.clearUserEmail()
            dataStoreRepository.clearUserId()
            Log.d("Logout", "User ID and email cleared")
            onLoggedOut()
        }
    }
}