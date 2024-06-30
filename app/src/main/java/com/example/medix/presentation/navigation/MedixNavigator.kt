package com.example.medix.presentation.navigation

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.medix.R
import com.example.medix.presentation.view.screens.app.appointment.AppointmentScreen
import com.example.medix.presentation.view.screens.app.appointment.AppointmentsViewModel
import com.example.medix.presentation.view.screens.app.change_patient_password.ChangePatientPassword
import com.example.medix.presentation.view.screens.app.doctor_details.DoctorDetailsScreen
import com.example.medix.presentation.view.screens.app.doctors.DoctorsScreen
import com.example.medix.presentation.view.screens.app.DoctorsViewModel
import com.example.medix.presentation.view.screens.app.doctors_search.SearchDoctorsScreen
import com.example.medix.presentation.view.screens.app.doctors_search.SearchDoctorsViewModel
import com.example.medix.presentation.view.screens.app.edit_patient_profile.EditPatientProfileScreen
import com.example.medix.presentation.view.screens.app.favourites.FavouritesScreen
import com.example.medix.presentation.view.screens.app.home.HomeScreen
import com.example.medix.presentation.view.screens.app.PatientsViewModel
import com.example.medix.presentation.view.screens.app.medix_ai.MedixAiScreen
import com.example.medix.presentation.view.screens.app.MedixAiViewModel
import com.example.medix.presentation.view.screens.app.medix_model.MedixModelScreen
import com.example.medix.presentation.view.screens.app.patient_appointments.PatientAppointmentsScreen
import com.example.medix.presentation.view.screens.app.patient_profile.PatientProfileScreen
import com.example.medix.presentation.view.screens.app.patient_profile.PatientProfileViewModel

@SuppressLint("AutoboxingStateCreation")
@Composable
fun MedixNavigator(
) {

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

    val medixAiViewModel : MedixAiViewModel = hiltViewModel()

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
            /*composable(route = Screens.PatientLoginRoute.route) {
                val currentBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = currentBackStackEntry?.destination?.route
                if (currentRoute == Screens.PatientProfileRoute.route) {
                    navController.navigate(Screens.PatientLoginRoute.route) {
                        popUpTo(Screens.PatientLoginRoute.route) {
                            inclusive = true
                        }
                    }
                } else {
                    PatientLogInScreen(authViewModel, navController)
                }
            }*/

            composable(
                route = Screens.HomeRoute.route,
            ) {
                val viewModel : DoctorsViewModel = hiltViewModel()
                val patientsViewModel : PatientsViewModel = hiltViewModel()
                HomeScreen(
                    navController = navController,
                    navigateToDoctorDetails = { doctorId ->
                        navigateToDoctorDetails(navController, doctorId)
                    },
                    doctorsViewModel = viewModel,
                    patientsViewModel = patientsViewModel
                )
            }

            composable(route = Screens.PatientAppointmentsRoute.route) {
                val appointmentsViewModel : AppointmentsViewModel = hiltViewModel()
                PatientAppointmentsScreen(
                    appointmentsViewModel = appointmentsViewModel
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
                //navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let { article ->
                val doctorViewModel : DoctorsViewModel = hiltViewModel()
                DoctorsScreen(
                    navigateUp = { navController.navigateUp() },
                    navigateToSearch = { navController.navigate(Screens.SearchDoctorsRoute.route) },
                    viewModel = doctorViewModel,
                    navigateToDoctorDetails = { doctorId ->
                        navigateToDoctorDetails(navController, doctorId)
                    }
                )
            }

            composable(
                route = Screens.SearchDoctorsRoute.route,
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
                val searchDoctorViewModel : SearchDoctorsViewModel = hiltViewModel()
                val state = searchDoctorViewModel.state.collectAsState()
                SearchDoctorsScreen(
                    state = state.value,
                    event = searchDoctorViewModel::onEvent,
                    navigateToDoctorDetails = { doctorId ->
                        navigateToDoctorDetails(navController, doctorId)
                    },
                    navigateUp = { navController.navigateUp() },
                    viewModel = searchDoctorViewModel

                )
            }

            composable(
                route = Screens.DoctorDetailsRoute.route,
                arguments = listOf(navArgument("doctorId") { type = NavType.IntType }),
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
                    backStackEntry ->
                val doctorId = backStackEntry.arguments?.getInt("doctorId") ?: return@composable
                DoctorDetailsScreen(
                    doctorId = doctorId,
                    navigateUp = { navController.popBackStack() },
                    navController = navController
                )
            }

            composable(
                route = Screens.AppointmentRoute.route,
                arguments = listOf(navArgument("doctorId") { type = NavType.IntType }),
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
                    backStackEntry ->
                val doctorId = backStackEntry.arguments?.getInt("doctorId") ?: return@composable
                AppointmentScreen(
                    doctorId = doctorId,
                    navigateUp = { navController.navigateUp() } ,
                    navController = navController,
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
                    viewModel = medixAiViewModel,
                    navController = navController
                )
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
                MedixModelScreen(
                    navigateUp = { navController.navigateUp() },
                    viewModel = medixAiViewModel,
                )
            }


            composable(route = Screens.FavouritesRoute.route) {
                FavouritesScreen(
                    navController = navController,
                )
            }

            composable(route = Screens.PatientProfileRoute.route) {
                val patientProfileViewModel : PatientProfileViewModel = hiltViewModel()
                val patientsViewModel : PatientsViewModel = hiltViewModel()
                PatientProfileScreen(
                    navController = navController,
                    patientsProfileViewModel = patientProfileViewModel,
                    patientsViewModel = patientsViewModel
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

private fun navigateToDoctorDetails(
    navController: NavController,
    doctorId: Int,
) {
    navController.navigate(
        route = Screens.DoctorDetailsRoute.route.replace("{doctorId}", doctorId.toString())
    )
}