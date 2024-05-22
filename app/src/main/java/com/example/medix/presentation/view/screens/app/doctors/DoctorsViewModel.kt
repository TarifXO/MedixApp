package com.example.medix.presentation.view.screens.app.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import com.example.medix.domain.useCases.doctors.GetDoctorByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(
    private val doctorsUseCase: DoctorsUseCases
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    val doctors: Flow<PagingData<Doctor>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                doctorsUseCase.getDoctors(listOf())
            } else {
                doctorsUseCase.searchDoctors(query, listOf())
            }
        }
        .cachedIn(viewModelScope)

    fun searchDoctors(name: String) {
        _searchQuery.value = name
    }

    private val getDoctorByIdUseCase: GetDoctorByIdUseCase by lazy {
        GetDoctorByIdUseCase(doctorsUseCase.doctorsRepository)
    }

    fun getDoctorById(id: Int): Flow<Doctor> = flow {
        emit(getDoctorByIdUseCase(id))
    }.flowOn(Dispatchers.IO)

}