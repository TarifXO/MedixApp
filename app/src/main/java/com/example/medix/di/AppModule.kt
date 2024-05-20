package com.example.medix.di

import com.example.medix.data.remote.MedixApi
import com.example.medix.data.repository.DoctorsRepositoryImpl
import com.example.medix.domain.repository.DoctorsRepository
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import com.example.medix.domain.useCases.doctors.GetDoctorsUseCase
import com.example.medix.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /*@Provides
    @Singleton
    fun provideMedixApi(): MedixApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MedixApi::class.java)
    }*/

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMedixApi(retrofit: Retrofit): MedixApi {
        return retrofit.create(MedixApi::class.java)
    }

    /*@Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl*/

    /*@Provides
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
    }*/

    @Provides
    @Singleton
    fun provideDoctorsUseCases(
        doctorsRepository: DoctorsRepository
    ) : DoctorsUseCases {
        return DoctorsUseCases(
            getDoctors = GetDoctorsUseCase(doctorsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDoctorsRepository(medixApi: MedixApi): DoctorsRepository = DoctorsRepositoryImpl(medixApi)
}