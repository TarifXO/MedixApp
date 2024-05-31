package com.example.medix.presentation.view.screens.app.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.domain.model.Patient
import com.example.medix.domain.repository.DataStoreRepository
import com.example.medix.domain.useCases.patients.PatientsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val patientsUseCases: PatientsUseCases,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){

    private val _patient = MutableLiveData<Patient?>()
    val selectedPatient: LiveData<Patient?> = _patient

    init {
        viewModelScope.launch {
            val userId = dataStoreRepository.getUserId() ?: return@launch
            fetchPatientById(userId)
        }
    }

    private fun fetchPatientById(id: Int) {
        viewModelScope.launch {
            try {
                val call = patientsUseCases.getPatientById(id)
                call.enqueue(object : retrofit2.Callback<Patient> {
                    override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                        if (response.isSuccessful) {
                            _patient.postValue(response.body())
                        } else {
                            _patient.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<Patient>, t: Throwable) {
                        _patient.postValue(null)
                    }
                })
            } catch (e: Exception) {
                _patient.postValue(null)
            }
        }
    }
}