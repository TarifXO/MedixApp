package com.example.medix.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medix.R
import com.example.medix.domain.model.DoctorAppointmentResponse
import com.example.medix.presentation.Dimens.doctorCardSize
import com.example.medix.presentation.Dimens.extraSmallPadding2
import com.example.medix.presentation.view.screens.app.appointment.AppointmentsViewModel
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange

@Composable
fun DoctorAppointmentCard(
    appointment: DoctorAppointmentResponse,
){

    var showMenu by remember { mutableStateOf(false) }
    val appointmentsViewModel : AppointmentsViewModel = hiltViewModel()
    val parsedDateTime = reverseParseDateTime(appointment.date, appointment.time)
    val dateParts = parsedDateTime["Date"]?.split(", ")
    val dayOfMonth = dateParts?.get(1)?.split(" ")?.get(0)
    val month = dateParts?.get(1)?.split(" ")?.get(1)
    val time = parsedDateTime["Time"]

    Row(modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, shape = RoundedCornerShape(12.dp))
        .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Box(modifier = Modifier
            .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
            .size(doctorCardSize)
            .clip(MaterialTheme.shapes.medium)
            .background(lightMixture),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dayOfMonth ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = orange,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = month ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                .width(160.dp)
                .height(doctorCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = appointment.patientName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = blackText,
                maxLines = 1,
            )

            appointment.patientPhone?.let {
                Text(text = it,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = mixture
                )
            }

            Spacer(modifier = Modifier.width(extraSmallPadding2))

            Text(text = time ?: "",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = mixture
            )
        }

        Spacer(modifier = Modifier.width(extraSmallPadding2))

        Box(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 0.dp),
        ) {
            IconButton(onClick = { showMenu = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.drop_down_icon),
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    showMenu = false
                    appointmentsViewModel.deleteAppointment(appointment.appointmentId)
                },
                    text = {
                        Text("Cancel Appointment")
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun DoctorAppointmentCardPreview(){
    MedixTheme {
        DoctorAppointmentCard(
            appointment = DoctorAppointmentResponse(
                appointmentId = 1,
                date = "24/02/2022",
                time = "6:00 PM",
                patientName = "John Doe",
                patientPhone = "01147940368",
                patientEmail = "",
                patientImage = ""
            )
        )
    }
}