package com.example.medix.domain.useCases.favorites

import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.domain.repository.FavoritesRepository

class GetFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(patientId: Int): List<FavoriteDoctorResponse> {
        return favoritesRepository.getFavorites(patientId)
    }
}