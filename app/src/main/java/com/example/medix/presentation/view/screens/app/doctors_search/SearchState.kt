package com.example.medix.presentation.view.screens.app.doctors_search

import com.example.medix.domain.model.Doctor

data class SearchState(
    val searchQuery: String = "",
    val doctors: List<Doctor> = emptyList()
)