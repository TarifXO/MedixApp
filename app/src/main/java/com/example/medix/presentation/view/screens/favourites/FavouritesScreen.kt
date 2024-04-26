package com.example.medix.presentation.view.screens.favourites

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.generateFakePagingItems
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.DoctorCard
import com.example.medix.presentation.view.components.DoctorsList
import com.example.medix.presentation.view.components.PatientAppointmentCard
import com.example.medix.presentation.view.components.TopBarTitleOnly
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun FavouritesScreen(
    navController: NavController,
    doctors: List<Doctor>,
){
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
                .background(mixture)
        ) {
            TopBarTitleOnly(
                title = "Favourites"
            )
        }

        DoctorsList(
            modifier = Modifier
                .padding(20.dp, top = 20.dp, end = 20.dp, bottom = 0.dp),
            doctors = doctors,
            onClick = {
                navController.navigate(Screens.DoctorDetailsRoute.route)
            }
        )
    }
}

@Preview
@Composable
fun FavouritesScreenPreview(){
    val fakePagingItems = generateFakePagingItems(20)
    FavouritesScreen(
        navController = rememberNavController(),
        doctors = fakePagingItems,
    )
}