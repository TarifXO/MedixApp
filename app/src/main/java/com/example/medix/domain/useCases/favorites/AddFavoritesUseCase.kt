package com.example.medix.domain.useCases.favorites

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.FavoritesRequest
import com.example.medix.domain.model.FavoritesResponse
import com.example.medix.domain.repository.FavoritesRepository

class AddFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(favoritesRequest: FavoritesRequest) : Resource<FavoritesResponse> {
        return try {
            val response = favoritesRepository.addFavorite(favoritesRequest)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}