package com.example.medix.domain.repository

import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.domain.model.FavoritesDeleteResponse
import com.example.medix.domain.model.FavoritesRequest
import com.example.medix.domain.model.FavoritesResponse

interface FavoritesRepository {
    suspend fun addFavorite(favoritesRequest: FavoritesRequest): FavoritesResponse
    suspend fun getFavorites(patientId: Int): List<FavoriteDoctorResponse>
    suspend fun deleteFavorite(favoriteId: Int): FavoritesResponse
}