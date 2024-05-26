package com.example.medix.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.medix.data.remote.MedixApi
import com.example.medix.domain.model.Doctor
import okhttp3.RequestBody.Companion.toRequestBody

/*class DoctorsByName(
    private val medixApi: MedixApi,
    private val name: String
) : PagingSource<Int, Doctor>() {

    override fun getRefreshKey(state: PagingState<Int, Doctor>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doctor> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val allDoctors = medixApi.searchDoctor(name.toRequestBody())
            if (allDoctors.isEmpty()) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

            val start = (page - 1) * pageSize
            val end = minOf(start + pageSize, allDoctors.size)
            val paginatedDoctors = if (start < end) {
                allDoctors.subList(start, end)
            } else {
                emptyList()
            }

            LoadResult.Page(
                data = paginatedDoctors,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (end == allDoctors.size) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("DoctorsPagingSource", "Error loading data", e)
            LoadResult.Error(e)
        }
    }
}*/