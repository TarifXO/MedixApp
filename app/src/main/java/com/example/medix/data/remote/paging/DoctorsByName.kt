package com.example.medix.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.Doctor
import okhttp3.RequestBody.Companion.toRequestBody

class DoctorsByName(
    private val medixApi: MedixApi,
    private val name: String
) : PagingSource<Int, Doctor>() {

    override fun getRefreshKey(state: PagingState<Int, Doctor>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doctor> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val doctors = medixApi.searchDoctor(name.toRequestBody())
            val start = (page - 1) * pageSize
            val end = minOf(start + pageSize, doctors.size)
            val paginatedDoctors = doctors.subList(start, end)

            LoadResult.Page(
                data = paginatedDoctors,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (end >= doctors.size) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}