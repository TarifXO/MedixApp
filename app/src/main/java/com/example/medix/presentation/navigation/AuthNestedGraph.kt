package com.example.medix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.medix.presentation.view.screens.LogInScreen
import com.example.medix.presentation.view.screens.PatientSignUpScreen
import com.example.medix.presentation.view.screens.RegisterScreen
import com.example.medix.presentation.view.screens.SignUpOptions

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = Screens.RegisterRoute.route, route = Screens.AuthRoute.route) {

        composable(Screens.RegisterRoute.route) {
            RegisterScreen(navController)
        }
        composable(Screens.PatientSignUpRoute.route) {
            PatientSignUpScreen(navController)
        }
        composable(Screens.LoginRoute.route) {
            LogInScreen(navController)
        }
        composable(Screens.SignUpOptionsRoute.route) {
            SignUpOptions(navController)
        }
    }
}