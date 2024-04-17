package com.example.medix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        authGraph(navController)


        navigation(
            route = Screens.MedixNavigation.route,
            startDestination = Screens.MedixNavigatorScreen.route
        ){
            composable(route = Screens.MedixNavigatorScreen.route){
                MedixNavigator()
            }
        }
    }
}