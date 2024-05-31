package com.example.medix

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
            startDestination = if (email.isNullOrEmpty()) {
                Screens.AuthRoute.route
            } else {
                Screens.MedixNavigation.route
            }
            delay(300)
            splashCondition = false
        }
    }
}