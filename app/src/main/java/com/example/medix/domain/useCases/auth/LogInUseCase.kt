package com.example.medix.domain.useCases.auth

/*class LogInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String): Resource<User> {
        return try {
            val result = authRepository.login(email, password)
            if (result is Resource.Success) {
                Resource.Success(result.data)
            } else {
                Resource.Failure(Exception("Failed to login"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}*/