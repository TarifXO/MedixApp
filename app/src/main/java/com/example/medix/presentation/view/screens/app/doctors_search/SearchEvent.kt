package com.example.medix.presentation.view.screens.app.doctors_search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    object SearchDoctors : SearchEvent()
}