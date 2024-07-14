package com.example.medix.presentation.view.screens.app.appointment

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medix.R
import com.example.medix.domain.model.AppointmentRequest
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.DayHourSelection
import com.example.medix.presentation.view.components.DaySelection
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.presentation.view.screens.app.PatientsViewModel
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun AppointmentScreen(
    doctorId: Int,
    navigateUp : () -> Unit,
    navController: NavController,
    appointmentsViewModel: AppointmentsViewModel = hiltViewModel(),
    patientsViewModel: PatientsViewModel = hiltViewModel(),
) {
    var selectedDay by remember { mutableStateOf("") }
    val defaultHour = "1:00 PM"
    var selectedHour by remember { mutableStateOf(defaultHour) }
    val context = LocalContext.current
    val user by patientsViewModel.selectedPatient.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(lightBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(80.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            TopBar(
                title = R.string.appointment.toString(),
                onBackClick = navigateUp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Spacer(modifier = Modifier.height(30.dp))

            DaySelection(
                onTitleSelected = { selectedDay = it }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = selectedDay,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = blackText,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = R.string.afternoon_dates.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = blackText,
                )

            Spacer(modifier = Modifier.height(10.dp))

            DayHourSelection(
                startTime = LocalTime.of(13, 0),
                endTime = LocalTime.of(16, 30),
                currentSelectedDate = selectedHour,
                onDateSelected = { selectedHour = it },
                onHourSelected = {
                    selectedHour = it
                }
            )

            Text(
                text = R.string.evening_dates.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = blackText,
            )

            Spacer(modifier = Modifier.height(10.dp))

            DayHourSelection(
                startTime = LocalTime.of(17, 0),
                endTime = LocalTime.of(20, 30),
                currentSelectedDate = selectedHour,
                onDateSelected = { selectedHour = it },
                onHourSelected = {
                    selectedHour = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            ElevatedButton(
                text = R.string.confirm.toString(),
                textSize = 20.sp,
                textColor = Color.White,
                backgroundColor = mixture,
                padding = PaddingValues(10.dp, top = 25.dp, bottom = 25.dp, end = 10.dp),
                onClick = {
                    val dateTimeComponents = parseDateTime(selectedDay, selectedHour)
                    val appointmentRequest = user?.let {
                        AppointmentRequest(
                            doctorId = doctorId,
                            patientId = it.id,
                            year = dateTimeComponents["Year"]!!,
                            month = dateTimeComponents["Month"]!!,
                            day = dateTimeComponents["Day"]!!,
                            hour = dateTimeComponents["Hour"]!!,
                            minute = dateTimeComponents["Minute"]!!
                        )
                    }
                    if (appointmentRequest != null) {
                        appointmentsViewModel.createAppointment(appointmentRequest)
                    }
                    navController.navigate(Screens.HomeRoute.route) {
                        popUpTo(Screens.HomeRoute.route) {
                            inclusive = true
                        }
                    }

                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(
                            context,
                            R.string.appointment_reserved_successfully.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }
}

fun parseDateTime(date: String, time: String): Map<String, Int> {
    val currentYear = Year.now().value
    val dateWithYear = "$date $currentYear"
    val formatterDate = DateTimeFormatter.ofPattern("EEEE, d MMM yyyy", Locale.ENGLISH)
    val parsedDate = LocalDate.parse(dateWithYear, formatterDate)

    val formatterTime = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
    val parsedTime = LocalTime.parse(time, formatterTime)

    return mapOf(
        "Year" to parsedDate.year,
        "Month" to parsedDate.monthValue,
        "Day" to parsedDate.dayOfMonth,
        "Hour" to parsedTime.hour,
        "Minute" to parsedTime.minute
    )
}