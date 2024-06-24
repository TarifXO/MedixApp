package com.example.medix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.medix.data.remote.MedixApi
import com.example.medix.data.remote.paging.DoctorsBySpecialization
import com.example.medix.data.remote.paging.DoctorsPagingSource
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.DoctorUser
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import javax.inject.Inject

class DoctorsRepositoryImpl @Inject constructor(
    private val medixApi: MedixApi
) : DoctorsRepository {

    override  fun getDoctors(): Flow<PagingData<Doctor>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30
            ),
            pagingSourceFactory = {
                DoctorsPagingSource(medixApi)
            }
        ).flow
    }

    override suspend fun searchDoctorsByName(
        name: String,
    ): List<Doctor> {
        return medixApi.searchDoctor(name)
    }

    override fun getDoctorById(id: Int): Call<Doctor> {
        return medixApi.getDoctorById(id)
    }

    override fun getDoctorUserById(id: Int): Call<DoctorUser> {
        return medixApi.getDoctorUserById(id)
    }

    override suspend fun getDoctorsBySpeciality(specialization: String): List<Doctor> {
        return medixApi.getDoctorBySpeciality(specialization)
    }
}