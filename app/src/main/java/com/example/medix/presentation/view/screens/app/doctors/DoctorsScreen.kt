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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ChipWithSubItems
import com.example.medix.presentation.view.components.DoctorsList
import com.example.medix.presentation.view.components.SearchBar
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorsScreen(
    navigateUp : () -> Unit,
    navController: NavController,
    viewModel: DoctorsViewModel = hiltViewModel()
){
    val searchText by viewModel.searchQuery.collectAsState()
    val doctors = viewModel.doctors.collectAsLazyPagingItems()
    val chipItems = listOf("Option 1", "Option 2", "Option 3")

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
                    text = searchText,
                    readOnly = false,
                    onValueChange = { viewModel.searchDoctors(it) },
                    )

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 15.dp, top = 20.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
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
            ChipWithSubItems(
                chipLabel = "Filter by",
                chipItems = chipItems
            )
            ChipWithSubItems(
                chipLabel = "Sort by",
                chipItems = chipItems
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        DoctorsList(
            modifier = Modifier.padding(horizontal = Dimens.mediumPadding1),
            doctors = doctors,
            onClick = { doctor ->
                navController.navigate("${Screens.DoctorDetailsRoute.route}/${doctor.id}")
            }
        )
    }
}

@Preview
@Composable
fun DoctorsScreenPreview() {
    DoctorsScreen(
        navigateUp = {},
        navController = rememberNavController()
    )
}