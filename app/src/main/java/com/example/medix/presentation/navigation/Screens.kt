package com.example.medix.presentation.navigation

sealed class Screens(val route : String) {
    data object RegisterRoute : Screens("Register")
    data object LoginRoute : Screens("LogIn")
    data object PatientSignUpRoute : Screens("PatientSignUp")
    data object DoctorSignUpRoute : Screens("DoctorSignUp")
    data object SignUpOptionsRoute : Screens("SignUpOptions")


    //////////

    data object HomeRoute : Screens("Home")
    data object PatientAppointmentsRoute : Screens("PatientAppointments")
    data object DoctorAppointmentsRoute : Screens("DoctorAppointments")
    data object FavouritesRoute : Screens("Favourites")
    data object PatientProfileRoute : Screens("PatientProfile")
    data object DoctorProfileRoute : Screens("DoctorProfile")
    data object DoctorsRoute : Screens("Doctors")
    data object SearchDoctorsRoute : Screens("SearchDoctors")
    data object MedixAiRoute : Screens("MedixAi")
    data object MedixModel : Screens("MedixModel")
    data object DoctorDetailsRoute : Screens("DoctorDetails/{doctorId}")
    data object AppointmentRoute : Screens("Appointment")
    data object EditPatientProfileRoute : Screens("EditPatientProfile")
    data object EditDoctorProfileRoute : Screens("EditDoctorProfile")
    data object ChangePatientPasswordRoute : Screens("ChangePatientPassword")
    data object ChangeDoctorPasswordRoute : Screens("ChangeDoctorPassword")

    ///////////

    data object AuthRoute : Screens("Auth")
    data object DoctorNavigation : Screens(route = "DoctorNavigation")
    data object MedixNavigation : Screens(route = "MedixNavigation")
    data object DoctorNavigatorScreen : Screens(route = "DoctorNavigator")
    data object MedixNavigatorScreen : Screens(route = "MedixNavigator")

}