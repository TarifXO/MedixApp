package com.example.medix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.medix.data.remote.MedixApi
import com.example.medix.data.remote.paging.DoctorsPagingSource
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.repository.DoctorsRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Response
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

    /*override fun getDoctorsBySpecialization(specialization: String, doctors : List<String>): Flow<PagingData<Doctor>> {
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
    }*/
}