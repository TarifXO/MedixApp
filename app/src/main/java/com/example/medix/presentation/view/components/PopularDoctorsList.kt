package com.example.medix.presentation.view.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.view.screens.common.EmptyScreen

@Composable
fun PopularDoctorsList(
    modifier: Modifier = Modifier,
    doctors : LazyPagingItems<Doctor>,
    onClick : (Doctor) -> Unit
) {
    val handlePagingResult = handlePopularDoctorsPagingResult(doctors = doctors)
    if (handlePagingResult) {
        LazyRow(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = Dimens.extraSmallPadding2)
        ) {
            items(doctors.itemCount) { index ->
                val doctor = doctors[index]
                if (doctor != null) {
                    PopularDoctorCard(doctor = doctor, onClick = onClick)
                } else {
                    // Log the null value occurrence
                    Log.e("DoctorsList", "Doctor at index $index is null")
                    Text(text = "Loading...")
                }
            }
        }
    }
}

@Composable
fun handlePopularDoctorsPagingResult(
    doctors : LazyPagingItems<Doctor>
) : Boolean {
    val loadState = doctors.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            PopularDoctorShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen(
                error = error
            )
            false
        }
        doctors.itemCount == 0 -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}

@Composable
private fun PopularDoctorShimmerEffect() {
    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.mediumPadding2)) {
        repeat(10){
            PopularDoctorCardShimmerEffect()
        }
    }
}