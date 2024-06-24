package com.example.medix.presentation.view.screens.app.doctors_search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDoctorsViewModel @Inject constructor(
    private val doctorsUseCase: DoctorsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

    private val _navigateToDoctorDetails = MutableLiveData<Int?>()
    val navigateToDoctorDetails: LiveData<Int?> = _navigateToDoctorDetails

    fun onDoctorClicked(doctorId: Int) {
        _navigateToDoctorDetails.value = doctorId
    }

    fun onDoctorDetailsNavigated() {
        _navigateToDoctorDetails.value = null
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchDoctors -> {
                searchDoctor(_state.value.searchQuery)
                getDoctorBySpecialization(_state.value.searchQuery)
            }
        }
    }

    private fun searchDoctor(name: String) {
        viewModelScope.launch {
            try {
                val doctors = doctorsUseCase.searchDoctors(name)
                _state.value = _state.value.copy(doctors = doctors) // Update this line
            } catch (e: Exception) {
                Log.e("SearchDoctorsViewModel", "Exception: $e")
                _state.value = _state.value.copy(doctors = emptyList()) // Handle null case appropriately
            }
        }
    }

    private fun getDoctorBySpecialization(specialization: String) {
        viewModelScope.launch {
            try {
                val doctors = doctorsUseCase.getDoctorsBySpeciality(specialization)
                _state.value = _state.value.copy(doctors = doctors) // Update this line
            } catch (e: Exception) {
                Log.e("SearchDoctorsViewModel", "Exception: $e")
                _state.value = _state.value.copy(doctors = emptyList()) // Handle null case appropriately
            }
        }
    }
}