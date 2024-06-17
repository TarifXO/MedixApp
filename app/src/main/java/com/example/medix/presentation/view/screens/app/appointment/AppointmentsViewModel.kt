package com.example.medix.presentation.view.screens.app.appointment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.domain.model.AppointmentResponse
import com.example.medix.domain.useCases.appointments.AppointmentsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val appointmentsUseCases: AppointmentsUseCases
) : ViewModel() {
    private val _appointmentState = MutableStateFlow<Resource<AppointmentResponse>>(Resource.Loading)

    fun createAppointment(appointmentRequest: AppointmentRequest) {
        viewModelScope.launch {
            _appointmentState.value = appointmentsUseCases.createAppointmentUseCase(appointmentRequest)
        }
    }
}
