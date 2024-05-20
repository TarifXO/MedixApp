package com.example.medix.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.Doctor

/*class DoctorsBySpecialization(
    private val medixApi: MedixApi,
    private val specialization: String
) : PagingSource<Int, Doctor>() {

    private var totalDoctorsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Doctor>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doctor> {
        val page = params.key ?: 1
        return try {
            val doctorResponse = medixApi.getDoctorsBySpecialization(specialization, page, pageSize = 10)
            totalDoctorsCount += doctorResponse.doctors.size
            val doctors = doctorResponse.doctors.distinctBy { it.name }
            LoadResult.Page(
                data = doctors,
                nextKey = if (totalDoctorsCount == doctorResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}*/