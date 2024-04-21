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
import com.example.medix.domain.model.generateFakePagingItems
import com.example.medix.presentation.view.screens.FavouritesScreen
import com.example.medix.presentation.view.screens.home.HomeScreen
import com.example.medix.presentation.view.screens.PatientAppointmentsScreen
import com.example.medix.presentation.view.screens.ProfileScreen
import com.example.medix.presentation.view.screens.appointment.AppointmentScreen
import com.example.medix.presentation.view.screens.doctor_details.DoctorDetails
import com.example.medix.presentation.view.screens.doctors.DoctorsScreen

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
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Screens.HomeRoute.route -> 0
            Screens.PatientAppointmentsRoute.route -> 1
            Screens.FavouritesRoute.route -> 2
            Screens.ProfileRoute.route -> 3
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Screens.HomeRoute.route ||
                backstackState?.destination?.route == Screens.PatientAppointmentsRoute.route ||
                backstackState?.destination?.route == Screens.FavouritesRoute.route ||
                backstackState?.destination?.route == Screens.ProfileRoute.route
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationBar(
                    items = bottomNavigationItem,
                    selected = selectedItem,
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
                                route = Screens.ProfileRoute.route
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
                    }
                )
            }

            composable(route = Screens.PatientAppointmentsRoute.route) {
                //val viewModel : SearchViewModel = hiltViewModel()
                //val state = viewModel.state.value
                PatientAppointmentsScreen(navController = navController)
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


            composable(route = Screens.FavouritesRoute.route) {
                //val viewModel : BookmarkViewModel = hiltViewModel()
                //val state = viewModel.state.value
                FavouritesScreen()
            }

            composable(route = Screens.ProfileRoute.route) {
                //val viewModel : BookmarkViewModel = hiltViewModel()
                //val state = viewModel.state.value
                ProfileScreen()
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