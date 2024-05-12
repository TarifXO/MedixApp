package com.example.medix.di

import com.example.medix.domain.repository.AuthRepository
import com.example.medix.data.repository.AuthRepositoryImpl
import com.example.medix.domain.useCases.auth.GetUserDataUseCase
import com.example.medix.domain.useCases.auth.LogInUseCase
import com.example.medix.domain.useCases.auth.LogOutUseCase
import com.example.medix.domain.useCases.auth.MedixUseCases
import com.example.medix.domain.useCases.auth.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideMedixUseCases(
        authRepository: AuthRepository
    ) : MedixUseCases {
        return MedixUseCases(
            logInUseCase = LogInUseCase(authRepository),
            signUpUseCase = SignUpUseCase(authRepository),
            getUserDataUseCase = GetUserDataUseCase(authRepository),
            logOutUseCase = LogOutUseCase(authRepository)
        )
    }
}