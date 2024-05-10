package com.example.medix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.medix.data.remote.MedixApi
import com.example.medix.data.remote.paging.DoctorsByName
import com.example.medix.data.remote.paging.DoctorsBySpecialization
import com.example.medix.data.remote.paging.DoctorsPagingSource
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow

class DoctorsRepositoryImpl(
    private val medixApi: MedixApi
) : DoctorsRepository {
    override  fun getDoctors(doctors : List<String>): Flow<PagingData<Doctor>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DoctorsPagingSource(medixApi)
            }
        ).flow
    }

    override suspend fun getDoctorById(id: Int, ): Doctor {
        return medixApi.getDoctorById(id)
    }

    override fun getDoctorsBySpecialization(specialization: String, doctors : List<String>): Flow<PagingData<Doctor>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DoctorsBySpecialization(medixApi, specialization) }
        ).flow
    }

    override  fun searchDoctorsByName(name: String, doctors : List<String>): Flow<PagingData<Doctor>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DoctorsByName(medixApi, name) }
        ).flow
    }
}