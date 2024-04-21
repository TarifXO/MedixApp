package com.example.medix.presentation.navigation

sealed class Screens(val route : String) {
    data object RegisterRoute : Screens("Register")
    data object LoginRoute : Screens("LogIn")
    data object PatientSignUpRoute : Screens("SignUp")
    data object SignUpOptionsRoute : Screens("SignUpOptions")
    data object HomeRoute : Screens("Home")
    data object AuthRoute : Screens("Auth")

    //////////

    data object PatientAppointmentsRoute : Screens("PatientAppointments")
    data object FavouritesRoute : Screens("Favourites")
    data object ProfileRoute : Screens("Profile")
    data object DoctorsRoute : Screens("Doctors")
    data object DoctorDetailsRoute : Screens("DoctorDetails")
    data object AppointmentRoute : Screens("Appointment")

    ///////////

    data object MedixNavigation : Screens(route = "MedixNavigation")
    data object MedixNavigatorScreen : Screens(route = "MedixNavigator")

}