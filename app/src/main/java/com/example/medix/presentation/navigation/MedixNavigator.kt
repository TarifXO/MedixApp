package com.example.medix.presentation.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medix.R
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.Patient
import com.example.medix.domain.model.generateFakePagingItems
import com.example.medix.presentation.view.screens.app.appointment.AppointmentScreen
import com.example.medix.presentation.view.screens.app.change_patient_password.ChangePatientPassword
import com.example.medix.presentation.view.screens.app.doctor_details.DoctorDetails
import com.example.medix.presentation.view.screens.app.doctors.DoctorsScreen
import com.example.medix.presentation.view.screens.app.edit_patient_profile.EditPatientProfileScreen
import com.example.medix.presentation.view.screens.app.favourites.FavouritesScreen
import com.example.medix.presentation.view.screens.app.home.HomeScreen
import com.example.medix.presentation.view.screens.app.medix_ai.MedixAiScreen
import com.example.medix.presentation.view.screens.app.medix_model.MedixModel
import com.example.medix.presentation.view.screens.app.patient_appointments.PatientAppointmentsScreen
import com.example.medix.presentation.view.screens.app.patient_profile.PatientProfileScreen

@SuppressLint("AutoboxingStateCreation")
@Composable
fun MedixNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.home_icon,
                selectedIcon = R.drawable.home_icon_filled,
                text = "Home"),
            BottomNavigationItem(
                icon = R.drawable.appointments_icon,
                selectedIcon = R.drawable.appointments_icon_filled,
                text = "Appointments"
            ),
            BottomNavigationItem(
                icon = R.drawable.favourites_icon,
                R.drawable.favourites_icon_filled,
                text = "Favourites"
            ),
            BottomNavigationItem(
                icon = R.drawable.profile_icon,
                R.drawable.profile_icon_filled,
                text = "Profile"
            )
        )
    }

    val navController = rememberNavController()
    val patientBackStackState = navController.currentBackStackEntryAsState().value
    var selectedPatientItem by rememberSaveable {
        mutableStateOf(0)
    }
    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    selectedPatientItem = remember(key1 = patientBackStackState) {
        when (patientBackStackState?.destination?.route) {
            Screens.HomeRoute.route -> 0
            Screens.PatientAppointmentsRoute.route -> 1
            Screens.FavouritesRoute.route -> 2
            Screens.PatientProfileRoute.route -> 3
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = patientBackStackState) {
        patientBackStackState?.destination?.route == Screens.HomeRoute.route ||
                patientBackStackState?.destination?.route == Screens.PatientAppointmentsRoute.route ||
                patientBackStackState?.destination?.route == Screens.FavouritesRoute.route ||
                patientBackStackState?.destination?.route == Screens.PatientProfileRoute.route
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                PatientBottomNavigationBar(
                    items = bottomNavigationItem,
                    selected = selectedPatientItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screens.HomeRoute.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screens.PatientAppointmentsRoute.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Screens.FavouritesRoute.route
                            )

                            3 -> navigateToTab(
                                navController = navController,
                                route = Screens.PatientProfileRoute.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Screens.HomeRoute.route,
            modifier = Modifier
                .padding(bottom = bottomPadding)
        ) {
            composable(
                route = Screens.HomeRoute.route,
            ) {
                //val viewModel : HomeViewModel = hiltViewModel()
                //val articles = viewModel.news.collectAsLazyPagingItems()
                val fakePagingItems = generateFakePagingItems(20)
                HomeScreen(
                    doctors = fakePagingItems,
                    navigateToDoctors = {
                        navigateToDoctors(
                            navController = navController,
                            doctor = fakePagingItems
                        )
                    },
                    navController = navController
                )
            }

            composable(route = Screens.PatientAppointmentsRoute.route) {
                //val viewModel : SearchViewModel = hiltViewModel()
                //val state = viewModel.state.value
                val fakePagingItems = generateFakePagingItems(20)
                PatientAppointmentsScreen(
                    doctors = fakePagingItems
                )
            }

            composable(
                route = Screens.DoctorsRoute.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                //val viewModel: DetailsViewModel = hiltViewModel()

                //navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let { article ->
                val fakePagingItems = remember { generateFakePagingItems(20) }
                DoctorsScreen(
                    navigateUp = { navController.navigateUp() },
                    doctors = fakePagingItems,
                    navController = navController
                )
                //}
            }
            
            composable(
                route = Screens.DoctorDetailsRoute.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                val doctor = Doctor(id = 1,
                    name = "tefoo",
                    description = "",
                    title = "Abdelrahman Tarif",
                    url = "",
                    urlToImage = "",)
                DoctorDetails(
                    navigateUp = { navController.navigateUp() },
                    navController = navController,
                    doctor = doctor
                )
            }


            composable(
                route = Screens.AppointmentRoute.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                AppointmentScreen(
                    navigateUp = { navController.navigateUp() } ,
                    navController = navController
                )
            }


            composable(
                route = Screens.MedixAiRoute.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                MedixAiScreen(
                    navigateUp = { navController.navigateUp() },
                ) { uri ->
                    selectedImageUri = uri
                    navController.navigate(Screens.MedixModel.route)
                }
            }


            composable(
                route = Screens.MedixModel.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                MedixModel(
                    navigateUp = { navController.navigateUp() },
                    selectedImageUri = selectedImageUri
                )
            }


            composable(route = Screens.FavouritesRoute.route) {
                //val viewModel : BookmarkViewModel = hiltViewModel()
                //val state = viewModel.state.value
                val fakePagingItems = remember { generateFakePagingItems(20) }
                FavouritesScreen(
                    navController = navController,
                    doctors = fakePagingItems,
                )
            }

            composable(route = Screens.PatientProfileRoute.route) {
                //val viewModel : BookmarkViewModel = hiltViewModel()
                //val state = viewModel.state.value
                val patient = Patient(id = 1,
                    name = "tefoo",
                    description = "",
                    title = "Abdelrahman Tarif",
                    url = "",
                    urlToImage = "",)
                PatientProfileScreen(
                    patient = patient,
                    navController = navController
                )
            }

            composable(
                route = Screens.EditPatientProfileRoute.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                EditPatientProfileScreen(
                    navigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }

            composable(
                route = Screens.ChangePatientPasswordRoute.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            -fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            fullWidth },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(100))
                }
            ) {
                //val viewModel : BookmarkViewModel = hiltViewModel()
                //val state = viewModel.state.value
                ChangePatientPassword(
                    navigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route : String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDoctors(
    navController: NavController,
    doctor: List<Doctor>,
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("doctor", doctor)
    navController.navigate(
        route = Screens.DoctorsRoute.route
    )
}