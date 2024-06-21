package com.example.medix.data.repository

import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.domain.model.FavoritesDeleteResponse
import com.example.medix.domain.model.FavoritesRequest
import com.example.medix.domain.model.FavoritesResponse
import com.example.medix.domain.repository.FavoritesRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val medixApi: MedixApi
) : FavoritesRepository {
    override suspend fun addFavorite(favoritesRequest: FavoritesRequest) : FavoritesResponse {
        val doctorId = favoritesRequest.doctorId.toString().toRequestBody("text/plain".toMediaType())
        val patientId = favoritesRequest.patientId.toString().toRequestBody("text/plain".toMediaType())
        return medixApi.addFavorite(doctorId, patientId)
    }

    override suspend fun getFavorites(patientId: Int): List<FavoriteDoctorResponse> {
        return medixApi.getFavorites(patientId)
    }

    override suspend fun deleteFavorite(favoriteId: Int): FavoritesResponse {
        return medixApi.deleteFavorite(favoriteId)
    }
}