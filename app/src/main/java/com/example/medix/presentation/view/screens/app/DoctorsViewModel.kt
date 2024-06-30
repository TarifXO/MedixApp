package com.example.medix.presentation.view.screens.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.DoctorUpdateRequest
import com.example.medix.domain.model.DoctorUser
import com.example.medix.domain.repository.DataStoreRepository
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import com.example.medix.domain.useCases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(
    private val doctorsUseCase: DoctorsUseCases,
    private val userUseCases: UserUseCases,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _navigateToDoctorDetails = MutableLiveData<Int?>()
    val navigateToDoctorDetails: LiveData<Int?> = _navigateToDoctorDetails

    private val _selectedDoctor = MutableLiveData<Doctor?>()
    val selectedDoctor: LiveData<Doctor?> = _selectedDoctor

    private val _doctor = MutableLiveData<DoctorUser?>()
    val doctor: LiveData<DoctorUser?> = _doctor


    init {
        viewModelScope.launch {
            delay(1000)
            val userId = dataStoreRepository.getDoctorId() ?: return@launch
            Log.d("DoctorsViewModel", "Init block: Retrieved user ID: $userId")
            fetchDoctorUserById(userId)
        }
    }

    fun onDoctorClicked(doctorId: Int) {
        _navigateToDoctorDetails.value = doctorId
    }

    fun onDoctorDetailsNavigated() {
        _navigateToDoctorDetails.value = null
    }

    fun getAllDoctors(): Flow<PagingData<Doctor>> {
        return doctorsUseCase.getDoctors()
    }

    fun fetchDoctorById(id: Int) {
        viewModelScope.launch {
            try {
                val call = doctorsUseCase.getDoctorById(id)
                call.enqueue(object : retrofit2.Callback<Doctor> {
                    override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                        if (response.isSuccessful) {
                            _selectedDoctor.postValue(response.body())
                        } else {
                            _selectedDoctor.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<Doctor>, t: Throwable) {
                        _selectedDoctor.postValue(null)
                    }
                })
            } catch (e: Exception) {
                _selectedDoctor.postValue(null)
            }
        }
    }

    private fun fetchDoctorUserById(id: Int) {
        viewModelScope.launch {
            try {
                val call = doctorsUseCase.getDoctorUserById(id)
                call.enqueue(object : retrofit2.Callback<DoctorUser> {
                    override fun onResponse(call: Call<DoctorUser>, response: Response<DoctorUser>) {
                        if (response.isSuccessful) {
                            _doctor.postValue(response.body())
                            Log.d("DoctorsViewModel", "Fetched doctor user: ${response.body()}")
                        } else {
                            _doctor.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<DoctorUser>, t: Throwable) {
                        _doctor.postValue(null)
                    }
                })
            } catch (e: Exception) {
                _doctor.postValue(null)
            }
        }
    }

    fun updateDoctor(updateRequest: DoctorUpdateRequest) {
        val doctorId = _doctor.value?.id ?: return
        viewModelScope.launch {
            try {
                userUseCases.updateDoctorUseCase.execute(doctorId, updateRequest)
                fetchDoctorUserById(doctorId)
            } catch (_: Exception) {
            }
        }
    }
}