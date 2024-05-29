package com.example.medix.di

import android.content.Context
import com.example.medix.data.remote.MedixApi
import com.example.medix.data.repository.DataStoreRepositoryImpl
import com.example.medix.data.repository.DoctorsRepositoryImpl
import com.example.medix.data.repository.UserRepositoryImpl
import com.example.medix.domain.repository.DataStoreRepository
import com.example.medix.domain.repository.DoctorsRepository
import com.example.medix.domain.repository.UserRepository
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import com.example.medix.domain.useCases.doctors.GetDoctorByIdUseCase
import com.example.medix.domain.useCases.doctors.GetDoctorsBySpecialization
import com.example.medix.domain.useCases.doctors.GetDoctorsUseCase
import com.example.medix.domain.useCases.doctors.SearchDoctorsUseCase
import com.example.medix.domain.useCases.user.ForgotPasswordUseCase
import com.example.medix.domain.useCases.user.LogInUseCase
import com.example.medix.domain.useCases.user.RegisterUseCase
import com.example.medix.domain.useCases.user.ResetPasswordUseCase
import com.example.medix.domain.useCases.user.UpdateDoctorUseCase
import com.example.medix.domain.useCases.user.UpdatePatientUseCase
import com.example.medix.domain.useCases.user.UserUseCases
import com.example.medix.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideUserUseCases(
        userRepository: UserRepository
    ) : UserUseCases {
        return UserUseCases(
            logInUseCase = LogInUseCase(userRepository),
            forgotPasswordUseCase = ForgotPasswordUseCase(userRepository),
            resetPasswordUseCase = ResetPasswordUseCase(userRepository),
            registerUseCase = RegisterUseCase(userRepository),
            updatePatientUseCase = UpdatePatientUseCase(userRepository),
            updateDoctorUseCase = UpdateDoctorUseCase(userRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDoctorsUseCases(
        doctorsRepository: DoctorsRepository
    ) : DoctorsUseCases {
        return DoctorsUseCases(
            getDoctors = GetDoctorsUseCase(doctorsRepository),
            searchDoctors = SearchDoctorsUseCase(doctorsRepository),
            getDoctorById = GetDoctorByIdUseCase(doctorsRepository),
            getDoctorsBySpeciality = GetDoctorsBySpecialization(doctorsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDoctorsRepository(medixApi: MedixApi): DoctorsRepository = DoctorsRepositoryImpl(medixApi)

    @Provides
    @Singleton
    fun provideUserRepository(medixApi: MedixApi): UserRepository = UserRepositoryImpl(medixApi)

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }
}