package com.example.medix.presentation.view.screens.app.appointment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.domain.model.AppointmentResponse
import com.example.medix.domain.model.PatientAppointmentsResponse
import com.example.medix.domain.useCases.appointments.AppointmentsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val appointmentsUseCases: AppointmentsUseCases
) : ViewModel() {
    private val _appointmentState = MutableStateFlow<Resource<AppointmentResponse>>(Resource.Loading)
    private val _patientAppointmentsState = MutableStateFlow<Resource<List<PatientAppointmentsResponse>>>(Resource.Loading)
    val patientAppointmentsState: StateFlow<Resource<List<PatientAppointmentsResponse>>> = _patientAppointmentsState

    fun createAppointment(appointmentRequest: AppointmentRequest) {
        viewModelScope.launch {
            _appointmentState.value = appointmentsUseCases.createAppointmentUseCase(appointmentRequest)
        }
    }

    fun getPatientAppointments(patientId: Int) {
        viewModelScope.launch {
            _patientAppointmentsState.value = Resource.Loading
            try {
                val appointments = appointmentsUseCases.patientAppointmentsUseCase(patientId)
                _patientAppointmentsState.value = Resource.Success(appointments)
            } catch (e: Exception) {
                Log.e("AppointmentsViewModel", "Error fetching appointments", e)
                _patientAppointmentsState.value = Resource.Failure(e)
            }
        }
    }
}