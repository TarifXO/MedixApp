package com.example.medix.presentation.view.screens.app.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.FavoriteDoctorResponse
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.view.components.DoctorCard
import com.example.medix.presentation.view.components.TopBarTitleOnly
import com.example.medix.presentation.view.screens.app.PatientsViewModel
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun FavouritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    patientsViewModel: PatientsViewModel = hiltViewModel(),
    ){
    val user by patientsViewModel.selectedPatient.observeAsState()
    val favoritesState by favoritesViewModel.patientFavoriteState.collectAsState()

    LaunchedEffect(user?.id) {
        user?.let { favoritesViewModel.getPatientFavorites(it.id) }
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
                .height(80.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            TopBarTitleOnly(
                title = "Favourites"
            )
        }

        when (val result = favoritesState) {
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Loading...")
                }
            }

            is Resource.Success -> {
                val favorites = result.data
                if (favorites.isEmpty()){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No Favorites Yet!")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp, top = 20.dp, end = 20.dp, bottom = 0.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1),
                        contentPadding = PaddingValues(all = Dimens.extraSmallPadding2)
                    ) {
                        items(favorites) { favorite ->
                            val doctor = mapFavoriteDoctorResponseToDoctor(favorite)
                            DoctorCard(
                                doctor = doctor,
                                onClick = { },
                                onFavoriteClick = {
                                    user?.let {
                                        favoritesViewModel.handleFavoriteClick(doctor.id, it.id)
                                    }
                                },
                                isFavoriteInitially = favoritesViewModel.isDoctorFavorite(doctor.id)
                            )
                        }
                    }
                }
            }

            is Resource.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "You have no favorites yet!")
                }
            }
        }
    }
}

fun mapFavoriteDoctorResponseToDoctor(favoriteDoctorResponse: FavoriteDoctorResponse): Doctor {
    return Doctor(
        id = favoriteDoctorResponse.id,
        name = favoriteDoctorResponse.name,
        phone = favoriteDoctorResponse.phone,
        email = favoriteDoctorResponse.email,
        speciality = favoriteDoctorResponse.speciality,
        bio = favoriteDoctorResponse.bio,
        address = favoriteDoctorResponse.address,
        wage = favoriteDoctorResponse.wage,
        image = favoriteDoctorResponse.image
    )
}

@Preview
@Composable
fun FavouritesScreenPreview(){
    FavouritesScreen(
        navController = rememberNavController(),
    )
}