package com.example.medix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.medix.presentation.view.screens.auth.AuthViewModel
import com.example.medix.presentation.view.screens.auth.doctor_sign_up.DoctorSignUpScreen
import com.example.medix.presentation.view.screens.auth.log_in.LogInScreen
import com.example.medix.presentation.view.screens.auth.patient_sign_up.PatientSignUpScreen
import com.example.medix.presentation.view.screens.auth.register.RegisterScreen
import com.example.medix.presentation.view.screens.auth.sign_up_options.SignUpOptions

fun NavGraphBuilder.authGraph(
    viewModel: AuthViewModel?,
    navController: NavController
) {
    navigation(startDestination = Screens.RegisterRoute.route, route = Screens.AuthRoute.route) {

        composable(Screens.RegisterRoute.route) {
            RegisterScreen(navController)
        }
        composable(Screens.PatientSignUpRoute.route) {
            PatientSignUpScreen(viewModel, navController)
        }
        composable(Screens.DoctorSignUpRoute.route) {
            DoctorSignUpScreen(navController)
        }
        composable(Screens.LoginRoute.route) {
            LogInScreen(viewModel, navController)
        }
        composable(Screens.SignUpOptionsRoute.route) {
            SignUpOptions(navController)
        }
    }
}