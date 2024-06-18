package com.example.medix.data.authentication

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Throwable) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}