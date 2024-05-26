package com.example.medix.presentation.view.screens.app.doctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(
    private val doctorsUseCase: DoctorsUseCases
) : ViewModel() {

    private val _navigateToDoctorDetails = MutableLiveData<Int?>()
    val navigateToDoctorDetails: LiveData<Int?> = _navigateToDoctorDetails

    private val _selectedDoctor = MutableLiveData<Doctor?>()
    val selectedDoctor: LiveData<Doctor?> = _selectedDoctor

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
                            // Handle unsuccessful response
                            _selectedDoctor.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<Doctor>, t: Throwable) {
                        // Handle failure
                        _selectedDoctor.postValue(null)
                    }
                })
            } catch (e: Exception) {
                // Handle exception
                _selectedDoctor.postValue(null)
            }
        }
    }
}