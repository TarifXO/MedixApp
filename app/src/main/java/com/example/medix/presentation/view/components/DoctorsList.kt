package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.Dimens


@Composable
fun DoctorsList(
    modifier: Modifier = Modifier,
    doctors : List<Doctor>,
    onClick : (Doctor) -> Unit
) {
    //val handlePagingResult = handlePagingResult(doctors = doctors)
    //if (handlePagingResult) {
    LazyColumn(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1),
        contentPadding = PaddingValues(all = Dimens.extraSmallPadding2)
    ) {
        items(count = doctors.lastIndex) { it ->
            DoctorCard(doctor = doctors[it]) { onClick(it) }
        }
    }
    //}
}

/*@Composable
fun handlePagingResult(
    doctors : List<Doctor>
) : Boolean {
    val loadState = doctors.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            /*EmptyScreen(
                error = error
            )*/
            false
        }
        doctors.itemCount == 0 -> {
            //EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}*/

/*@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding2)) {
        repeat(10){
            DoctorCardShimmerEffect(
                modifier = Modifier
                    .padding(horizontal = mediumPadding1)
            )
        }
    }
}*/
