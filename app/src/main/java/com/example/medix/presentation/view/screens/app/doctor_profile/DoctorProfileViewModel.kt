package com.example.medix.presentation.view.screens.app.doctor_profile

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.MainActivity
import com.example.medix.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val application: Application,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            dataStoreRepository.clearDoctorEmail()
            dataStoreRepository.clearDoctorId()
            Log.d("Logout", "User ID and email cleared")

            val intent = Intent(application, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            application.startActivity(intent)
        }
    }
}