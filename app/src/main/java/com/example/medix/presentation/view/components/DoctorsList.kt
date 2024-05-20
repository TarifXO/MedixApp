package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.Dimens.extraSmallPadding2
import com.example.medix.presentation.Dimens.mediumPadding1
import com.example.medix.presentation.Dimens.mediumPadding2
import com.example.medix.presentation.view.screens.common.EmptyScreen

@Composable
fun DoctorsList(
    modifier: Modifier = Modifier,
    doctors : List<Doctor>,
    onClick : (Doctor) -> Unit
) {
    if (doctors.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(mediumPadding1),
        contentPadding = PaddingValues(all = extraSmallPadding2)
    ) {
        items(count = doctors.size) {
            val doctor = doctors[it]
            DoctorCard(doctor = doctor, onClick =  { onClick(doctor) })
        }
    }
}


@Composable
fun DoctorsList(
    modifier: Modifier = Modifier,
    doctors : LazyPagingItems<Doctor>,
    onClick : (Doctor) -> Unit
) {
    val handlePagingResult = handlePagingResult(doctors = doctors)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
            contentPadding = PaddingValues(all = extraSmallPadding2)
        ) {
            items(count = doctors.itemCount) { it ->
                doctors[it]?.let {
                    DoctorCard(doctor = it) { onClick(it) }
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
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
            ShimmerEffect()
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
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding2)) {
        repeat(10){
            DoctorCardShimmerEffect(
                modifier = Modifier
                    .padding(horizontal = mediumPadding1)
            )
        }
    }
}