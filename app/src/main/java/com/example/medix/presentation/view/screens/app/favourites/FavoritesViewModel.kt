package com.example.medix.presentation.view.screens.app.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.domain.model.FavoritesRequest
import com.example.medix.domain.model.FavoritesResponse
import com.example.medix.domain.useCases.favorites.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCases: FavoritesUseCases
) : ViewModel() {

    private val _favoriteState = MutableStateFlow<Resource<FavoritesResponse>>(Resource.Loading)
    private val _patientFavoriteState = MutableStateFlow<Resource<List<FavoriteDoctorResponse>>>(Resource.Loading)
    val patientFavoriteState: StateFlow<Resource<List<FavoriteDoctorResponse>>> = _patientFavoriteState

    fun addFavorite(favoritesRequest: FavoritesRequest) {
        viewModelScope.launch {
            _favoriteState.value = favoritesUseCases.addFavoriteUseCase(favoritesRequest)
        }
    }

    fun getPatientFavorites(patientId: Int) {
        viewModelScope.launch {
            _patientFavoriteState.value = Resource.Loading
            try {
                val favorites = favoritesUseCases.getFavoritesUseCase(patientId)
                _patientFavoriteState.value = Resource.Success(favorites)
            } catch (e: Exception) {
                _patientFavoriteState.value = Resource.Failure(e)
            }
        }
    }
}