package com.example.medix.presentation.view.screens.app.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.medix.R
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.PopularDoctorsList
import com.example.medix.presentation.view.screens.app.PatientsViewModel
import com.example.medix.presentation.view.screens.app.DoctorsViewModel
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange


@Composable
fun HomeScreen(
    navController: NavController,
    doctorsViewModel: DoctorsViewModel? = hiltViewModel(),
    patientsViewModel: PatientsViewModel = hiltViewModel(),
    navigateToDoctorDetails: (Int) -> Unit,
) {
    val context = LocalContext.current
    val doctors = doctorsViewModel!!.getAllDoctors().collectAsLazyPagingItems()
    val navigateToDoctorDetailsScreen = doctorsViewModel.navigateToDoctorDetails.observeAsState()
    val user by patientsViewModel.selectedPatient.observeAsState()

    LaunchedEffect(navigateToDoctorDetailsScreen.value) {
            navigateToDoctorDetailsScreen.value?.let { doctorId ->
                navigateToDoctorDetails(doctorId)
                doctorsViewModel.onDoctorDetailsNavigated()
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(lightBackground),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .height(200.dp)
            .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(start = 25.dp, end = 25.dp)
                .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Row {
                        Text(text = context.getString(R.string.hellohome),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        )
                        Text(text = "Welcome",
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp,
                                color = orange
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    if (user != null) {
                        Text(
                            text = user!!.name,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        )
                    }

                }

                if (user != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context).data(user!!.image)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(MaterialTheme.shapes.large),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Popular Doctors",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = blackText
                    )
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate(Screens.DoctorsRoute.route)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "See all",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = mixture
                        )
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }

            PopularDoctorsList(
                doctors = doctors,
                onClick = { doctor ->
                    doctor.id.let { doctorsViewModel.onDoctorClicked(it) }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp, 200.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable { navController.navigate(Screens.DoctorsRoute.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.doctor_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            alignment = Alignment.TopCenter
                        )

                        Text(
                            text = "Doctors",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                color = blackText
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                        )

                        Text(
                            text = "Surgeons, Cardiologists,\n" +
                                    "Dentists, Pediatricians...",
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = blackText
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(150.dp, 200.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable { navController.navigate(Screens.MedixAiRoute.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.medix_ai_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            alignment = Alignment.TopCenter
                        )

                        Text(
                            text = "Brain Tumor Diagnosis",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                color = blackText
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Consult our AI model for Initial diagnosis.",
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = blackText
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
        }
    }

}