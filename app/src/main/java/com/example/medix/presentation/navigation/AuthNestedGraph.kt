package com.example.medix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.medix.presentation.view.screens.auth.AuthViewModel
import com.example.medix.presentation.view.screens.auth.doctor_log_in.DoctorLogInScreen
import com.example.medix.presentation.view.screens.auth.doctor_register.DoctorRegisterScreen
import com.example.medix.presentation.view.screens.auth.doctor_sign_up.DoctorSignUpScreen
import com.example.medix.presentation.view.screens.auth.patient_log_in.PatientLogInScreen
import com.example.medix.presentation.view.screens.auth.patient_sign_up.PatientSignUpScreen
import com.example.medix.presentation.view.screens.auth.patient_register.PatientRegisterScreen
import com.example.medix.presentation.view.screens.auth.sign_up_options.RegisterOptions

fun NavGraphBuilder.authGraph(
    viewModel: AuthViewModel?,
    navController: NavController
) {
    navigation(startDestination = Screens.RegisterOptions.route, route = Screens.AuthRoute.route) {

        composable(Screens.RegisterOptions.route) {
            RegisterOptions(navController)
        }
        composable(Screens.PatientRegisterRoute.route) {
            PatientRegisterScreen(navController)
        }
        composable(Screens.DoctorRegisterRoute.route) {
            DoctorRegisterScreen(navController)
        }
        composable(Screens.PatientSignUpRoute.route) {
            PatientSignUpScreen(viewModel, navController)
        }
        composable(Screens.DoctorSignUpRoute.route) {
            DoctorSignUpScreen(viewModel, navController)
        }
        composable(Screens.PatientLoginRoute.route) {
            PatientLogInScreen(viewModel, navController)
        }
        composable(Screens.DoctorLoginRoute.route) {
            DoctorLogInScreen(viewModel, navController)
        }
    }
}