package com.example.medix.domain.useCases.auth

/*class GetUserDataUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(): Resource<User> {
        return try {
            val userData = authRepository.getUserData()
            if (userData != null) {
                Resource.Success(userData)
            } else {
                Resource.Failure(Exception("User data not found"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}*/