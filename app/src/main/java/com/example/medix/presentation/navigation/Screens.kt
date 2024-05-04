package com.example.medix.presentation.navigation

sealed class Screens(val route : String) {
    data object RegisterRoute : Screens("Register")
    data object LoginRoute : Screens("LogIn")
    data object PatientSignUpRoute : Screens("SignUp")
    data object SignUpOptionsRoute : Screens("SignUpOptions")


    //////////

    data object HomeRoute : Screens("Home")
    data object PatientAppointmentsRoute : Screens("PatientAppointments")
    data object FavouritesRoute : Screens("Favourites")
    data object ProfileRoute : Screens("Profile")
    data object DoctorsRoute : Screens("Doctors")
    data object MedixAiRoute : Screens("MedixAi")
    data object MedixModel : Screens("MedixModel")
    data object DoctorDetailsRoute : Screens("DoctorDetails")
    data object AppointmentRoute : Screens("Appointment")
    data object EditPatientProfileRoute : Screens("EditPatientProfile")
    data object ChangePasswordRoute : Screens("ChangePassword")

    ///////////

    data object AuthRoute : Screens("Auth")
    data object MedixNavigation : Screens(route = "MedixNavigation")
    data object MedixNavigatorScreen : Screens(route = "MedixNavigator")

}