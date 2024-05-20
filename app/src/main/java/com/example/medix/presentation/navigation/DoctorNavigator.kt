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
import com.example.medix.domain.model.generateFakePagingItemsForPatients
import com.example.medix.presentation.view.screens.app.change_doctor_password.ChangeDoctorPassword
import com.example.medix.presentation.view.screens.app.doctor_appointments.DoctorAppointmentsScreen
import com.example.medix.presentation.view.screens.app.doctor_profile.DoctorProfileScreen
import com.example.medix.presentation.view.screens.app.edit_doctor_profile.EditDoctorProfileScreen
import com.example.medix.presentation.view.screens.auth.log_in.LogInScreen

@SuppressLint("AutoboxingStateCreation")
@Composable
fun DoctorNavigator(
    //viewModel: AuthViewModel?
) {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.appointments_icon,
                selectedIcon = R.drawable.appointments_icon_filled,
                text = "Appointments"
            ),
            BottomNavigationItem(
                icon = R.drawable.profile_icon,
                R.drawable.profile_icon_filled,
                text = "Profile"
            )
        )
    }

    val navController = rememberNavController()
    val doctorBackStackState = navController.currentBackStackEntryAsState().value
    var selectedDoctorItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedDoctorItem = remember(key1 = doctorBackStackState) {
        when (doctorBackStackState?.destination?.route) {
            Screens.DoctorAppointmentsRoute.route -> 0
            Screens.DoctorProfileRoute.route -> 1
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = doctorBackStackState) {
        doctorBackStackState?.destination?.route == Screens.DoctorAppointmentsRoute.route ||
                doctorBackStackState?.destination?.route == Screens.DoctorProfileRoute.route
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                DoctorBottomNavigationBar(
                    items = bottomNavigationItem,
                    selected = selectedDoctorItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screens.DoctorAppointmentsRoute.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screens.DoctorProfileRoute.route
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
            startDestination = Screens.DoctorAppointmentsRoute.route,
            modifier = Modifier
                .padding(bottom = bottomPadding)
        ) {
            composable(route = Screens.LoginRoute.route) {
                val currentBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = currentBackStackEntry?.destination?.route
                if (currentRoute == Screens.DoctorProfileRoute.route) {
                    navController.navigate(Screens.LoginRoute.route) {
                        popUpTo(Screens.LoginRoute.route) {
                            inclusive = true
                        }
                    }
                } else {
                    LogInScreen(navController)
                }
            }

            composable(route = Screens.DoctorAppointmentsRoute.route) {
                //val viewModel : SearchViewModel = hiltViewModel()
                //val state = viewModel.state.value
                val fakePagingItems = generateFakePagingItemsForPatients(20)
                DoctorAppointmentsScreen(
                    patients = fakePagingItems
                )
            }

            composable(route = Screens.DoctorProfileRoute.route) {
                //val viewModel : BookmarkViewModel = hiltViewModel()
                //val state = viewModel.state.value
                val doctor = Doctor(
                    id = 1,
                    speciality = "Dentist",
                    bio = "he is the best around here",
                    name = "Abdelrahman Tarif",
                    address = "",
                    phone = "0123456789",
                    dateOfBirth = "12/12/2023",
                    gender = "Male",
                    email = "",
                    image = "",
                    wage = 0.0,
                    favorites = 0,
                    appointments = 0
                )
                DoctorProfileScreen(
                    doctor = doctor,
                    navController = navController,
                    //viewModel = viewModel
                )
            }

            composable(
                route = Screens.EditDoctorProfileRoute.route,
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
                EditDoctorProfileScreen(
                    navigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }

            composable(
                route = Screens.ChangeDoctorPasswordRoute.route,
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
                ChangeDoctorPassword(
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