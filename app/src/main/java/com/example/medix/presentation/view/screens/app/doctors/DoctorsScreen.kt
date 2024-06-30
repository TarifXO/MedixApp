package com.example.medix.presentation.view.screens.app.doctors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.view.components.DoctorsList
import com.example.medix.presentation.view.components.SearchBar
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.presentation.view.screens.app.DoctorsViewModel
import com.example.medix.presentation.view.screens.app.favourites.FavoritesViewModel
import com.example.medix.presentation.view.screens.app.PatientsViewModel
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorsScreen(
    navigateUp : () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDoctorDetails : (Int) -> Unit,
    viewModel: DoctorsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    patientsViewModel: PatientsViewModel = hiltViewModel()
){
    val doctors = viewModel.getAllDoctors().collectAsLazyPagingItems()
    val user by patientsViewModel.selectedPatient.observeAsState()
    val navigateToDoctorDetailsScreen = viewModel.navigateToDoctorDetails.observeAsState()
    //val deleteFavoriteState by favoritesViewModel.deleteFavoriteState.collectAsState()

    LaunchedEffect(navigateToDoctorDetailsScreen.value) {
        navigateToDoctorDetailsScreen.value?.let { doctorId ->
            navigateToDoctorDetails(doctorId)
            viewModel.onDoctorDetailsNavigated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(lightBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(180.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
            ) {
            Column {
                TopBar(
                    title = "Doctors",
                    onBackClick = navigateUp
                )

                Spacer(modifier = Modifier.height(45.dp))

                SearchBar(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "",
                    readOnly = true,
                    onValueChange = {},
                    onClick = {
                        navigateToSearch()
                    },
                    onSearch = {}
                )

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 15.dp, top = 10.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Doctors",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = blackText
                ),
            )

            /*ChipWithSubItems(
                chipLabel = "Sort by",
                onSortOptionSelected = { },
            )*/
        }

        //Spacer(modifier = Modifier.padding(10.dp))

        DoctorsList(
            modifier = Modifier.padding(horizontal = Dimens.mediumPadding1),
            doctors = doctors,
            onClick = { doctor: Doctor ->
                doctor.id.let { viewModel.onDoctorClicked(it) }
            },
            onFavoriteClick = { doctor: Doctor ->
                user?.let {
                    favoritesViewModel.handleFavoriteClick(doctor.id, it.id)
                }
            }
        )
    }
}