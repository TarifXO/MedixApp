package com.example.medix.domain.useCases.favorites

data class FavoritesUseCases(
    val addFavoriteUseCase: AddFavoritesUseCase,
    val getFavoritesUseCase: GetFavoritesUseCase,
    val deleteFavoriteUseCase: DeleteFavoritesUseCase
)
