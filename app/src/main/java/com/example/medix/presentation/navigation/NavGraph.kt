package com.example.medix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.medix.presentation.view.screens.auth.AuthViewModel

@Composable
fun NavGraph(
    viewModel: AuthViewModel,
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        authGraph(viewModel = viewModel, navController)


        navigation(
            route = Screens.MedixNavigation.route,
            startDestination = Screens.MedixNavigatorScreen.route
        ){
            composable(route = Screens.MedixNavigatorScreen.route){
                MedixNavigator(
                    viewModel = viewModel,
                )
            }
        }

        navigation(
            route = Screens.DoctorNavigation.route,
            startDestination = Screens.DoctorNavigatorScreen.route
        ){
            composable(route = Screens.DoctorNavigatorScreen.route){
                DoctorNavigator(
                    viewModel = viewModel,
                )
            }
        }
    }
}