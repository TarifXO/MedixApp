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
            val email = dataStoreRepository.getUserEmail()
            val userId = dataStoreRepository.getUserId()
            Log.d("MainViewModel", "Retrieved user ID: $userId, Email: $email")
            startDestination = if (email.isNullOrEmpty() || userId == 0) {
                Screens.AuthRoute.route
            } else {
                Screens.MedixNavigation.route
            }
            delay(300)
            splashCondition = false
        }
    }
}