package com.example.medix.presentation.view.screens.app.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.domain.model.FavoritesDeleteResponse
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
    val favoriteState: StateFlow<Resource<FavoritesResponse>> = _favoriteState
    private val _patientFavoriteState = MutableStateFlow<Resource<List<FavoriteDoctorResponse>>>(Resource.Loading)
    val patientFavoriteState: StateFlow<Resource<List<FavoriteDoctorResponse>>> = _patientFavoriteState
    private val _deleteFavoriteState = MutableStateFlow<Resource<FavoritesResponse>>(Resource.Loading)
    val deleteFavoriteState: StateFlow<Resource<FavoritesResponse>> = _deleteFavoriteState

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

    fun deleteFavorite(favoriteId: Int) {
        Log.d("FavoritesViewModel", "deleteFavorite called with id: $favoriteId")
        viewModelScope.launch {
            _deleteFavoriteState.value = Resource.Loading
            try {
                val resource = favoritesUseCases.deleteFavoriteUseCase(favoriteId)
                if (resource is Resource.Success) {
                    _deleteFavoriteState.value = Resource.Success(resource.data)
                    Log.d("FavoritesViewModel", "Successfully deleted favorite with id $favoriteId")
                } else if (resource is Resource.Failure) {
                    _deleteFavoriteState.value = Resource.Failure(resource.exception)
                    Log.e("FavoritesViewModel", "Failed to delete favorite with id $favoriteId", resource.exception)
                }
            } catch (e: Exception) {
                _deleteFavoriteState.value = Resource.Failure(e)
                Log.e("FavoritesViewModel", "Exception when deleting favorite with id $favoriteId", e)
            }
        }
    }

    fun isDoctorFavorite(doctorId: Int): Boolean {
        val favorites = (_patientFavoriteState.value as? Resource.Success)?.data ?: return false
        return favorites.any { it.id == doctorId }
    }

    fun toggleFavorite(favoritesResponse: FavoritesResponse) {
        viewModelScope.launch {
            val favorites = favoritesUseCases.getFavoritesUseCase(favoritesResponse.patientId)
            val favorite = favorites.find { it.id == favoritesResponse.doctorId }
            if (favorite != null) {
                favoritesUseCases.deleteFavoriteUseCase(favorite.id)
            } else {
                favoritesUseCases.addFavoriteUseCase(
                    FavoritesRequest(favoritesResponse.doctorId, favoritesResponse.patientId)
                )
            }
        }
    }

    suspend fun getFavoriteId(favoritesRequest: FavoritesRequest): Int? {
        val favorites = favoritesUseCases.getFavoritesUseCase(favoritesRequest.patientId)
        val favorite = favorites.find { it.id == favoritesRequest.doctorId }
        return favorite?.id
    }

    fun handleFavoriteClick(doctorId: Int, patientId: Int): Boolean {
        var isAlreadyFavorite = false
        viewModelScope.launch {
            val favoritesRequest = FavoritesRequest(doctorId, patientId)
            val favoriteId = getFavoriteId(favoritesRequest)
            if (favoriteId == null) {
                if (!isDoctorFavorite(doctorId)) {
                    addFavorite(favoritesRequest)
                } else {
                    isAlreadyFavorite = true
                }
            }
        }
        return isAlreadyFavorite
    }
}