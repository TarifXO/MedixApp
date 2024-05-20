package com.example.medix.presentation.view.screens.app.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(
    doctorsUseCase: DoctorsUseCases
) : ViewModel() {

    val doctors : Flow<PagingData<Doctor>> = doctorsUseCase.getDoctors(listOf()).cachedIn(viewModelScope)

}