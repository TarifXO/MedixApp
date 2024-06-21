package com.example.medix.domain.useCases.favorites

import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.FavoritesDeleteResponse
import com.example.medix.domain.model.FavoritesResponse
import com.example.medix.domain.repository.FavoritesRepository

class DeleteFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(favoriteId: Int) : Resource<FavoritesResponse> {
        return try {
            val response = favoritesRepository.deleteFavorite(favoriteId)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}