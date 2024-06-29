package com.example.medix.di

import android.content.Context
import com.example.medix.data.remote.MedixApi
import com.example.medix.data.repository.AppointmentsRepositoryImpl
import com.example.medix.data.repository.DataStoreRepositoryImpl
import com.example.medix.data.repository.DoctorsRepositoryImpl
import com.example.medix.data.repository.FavoritesRepositoryImpl
import com.example.medix.data.repository.PatientsRepositoryImpl
import com.example.medix.data.repository.UserRepositoryImpl
import com.example.medix.domain.repository.AppointmentsRepository
import com.example.medix.domain.repository.DataStoreRepository
import com.example.medix.domain.repository.DoctorsRepository
import com.example.medix.domain.repository.FavoritesRepository
import com.example.medix.domain.repository.PatientsRepository
import com.example.medix.domain.repository.UserRepository
import com.example.medix.domain.useCases.ai.AiModelUseCase
import com.example.medix.domain.useCases.appointments.AppointmentsUseCases
import com.example.medix.domain.useCases.appointments.CreateAppointmentUseCase
import com.example.medix.domain.useCases.appointments.DeleteAppointmentUseCase
import com.example.medix.domain.useCases.appointments.DoctorAppointmentsUseCase
import com.example.medix.domain.useCases.appointments.PatientAppointmentsUseCase
import com.example.medix.domain.useCases.doctors.DoctorsUseCases
import com.example.medix.domain.useCases.doctors.GetDoctorByIdUseCase
import com.example.medix.domain.useCases.doctors.GetDoctorUserByIdUseCase
import com.example.medix.domain.useCases.doctors.GetDoctorsBySpecialization
import com.example.medix.domain.useCases.doctors.GetDoctorsUseCase
import com.example.medix.domain.useCases.doctors.SearchDoctorsUseCase
import com.example.medix.domain.useCases.favorites.AddFavoritesUseCase
import com.example.medix.domain.useCases.favorites.DeleteFavoritesUseCase
import com.example.medix.domain.useCases.favorites.FavoritesUseCases
import com.example.medix.domain.useCases.favorites.GetFavoritesUseCase
import com.example.medix.domain.useCases.patients.GetPatientByIdUseCase
import com.example.medix.domain.useCases.patients.PatientsUseCases
import com.example.medix.domain.useCases.user.ChangePasswordUseCase
import com.example.medix.domain.useCases.user.DoctorLogInUseCase
import com.example.medix.domain.useCases.user.ForgotPasswordUseCase
import com.example.medix.domain.useCases.user.PatientLogInUseCase
import com.example.medix.domain.useCases.user.RegisterUseCase
import com.example.medix.domain.useCases.user.ResetPasswordUseCase
import com.example.medix.domain.useCases.user.UpdateDoctorUseCase
import com.example.medix.domain.useCases.user.UpdatePatientUseCase
import com.example.medix.domain.useCases.user.UserUseCases
import com.example.medix.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
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
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
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
            patientLogInUseCase = PatientLogInUseCase(userRepository),
            doctorLogInUseCase = DoctorLogInUseCase(userRepository),
            forgotPasswordUseCase = ForgotPasswordUseCase(userRepository),
            resetPasswordUseCase = ResetPasswordUseCase(userRepository),
            changePasswordUseCase = ChangePasswordUseCase(userRepository),
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
            getDoctorUserById = GetDoctorUserByIdUseCase(doctorsRepository),
            getDoctorsBySpeciality = GetDoctorsBySpecialization(doctorsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDoctorsRepository(medixApi: MedixApi): DoctorsRepository = DoctorsRepositoryImpl(medixApi)

    @Provides
    @Singleton
    fun providePatientsUseCases(
        patientsRepository: PatientsRepository
    ) : PatientsUseCases {
        return PatientsUseCases(
            getPatientById = GetPatientByIdUseCase(patientsRepository),
        )
    }

    @Provides
    @Singleton
    fun providePatientsRepository(medixApi: MedixApi): PatientsRepository = PatientsRepositoryImpl(medixApi)

    @Provides
    @Singleton
    fun provideUserRepository(medixApi: MedixApi): UserRepository = UserRepositoryImpl(medixApi)

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideAppointmentsUseCases(appointmentsRepository: AppointmentsRepository
    ) : AppointmentsUseCases {
        return AppointmentsUseCases(
            createAppointmentUseCase = CreateAppointmentUseCase(appointmentsRepository),
            patientAppointmentsUseCase = PatientAppointmentsUseCase(appointmentsRepository),
            doctorAppointmentsUseCase = DoctorAppointmentsUseCase(appointmentsRepository),
            deleteAppointmentUseCase = DeleteAppointmentUseCase(appointmentsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAppointmentsRepository(medixApi: MedixApi): AppointmentsRepository = AppointmentsRepositoryImpl(medixApi)

    @Provides
    @Singleton
    fun provideFavoritesUseCases(favoritesRepository: FavoritesRepository
    ) : FavoritesUseCases {
        return FavoritesUseCases(
            addFavoriteUseCase = AddFavoritesUseCase(favoritesRepository),
            getFavoritesUseCase = GetFavoritesUseCase(favoritesRepository),
            deleteFavoriteUseCase = DeleteFavoritesUseCase(favoritesRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(medixApi: MedixApi): FavoritesRepository = FavoritesRepositoryImpl(medixApi)


    //Ai Model
    @Provides
    @Singleton
    fun provideAiModelUseCase(okHttpClient: OkHttpClient): AiModelUseCase {
        return AiModelUseCase(okHttpClient)
    }
}
