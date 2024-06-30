package com.example.medix.presentation.view.screens.app.doctor_appointments

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medix.data.authentication.Resource
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.view.components.DoctorAppointmentCard
import com.example.medix.presentation.view.components.TopBarTitleOnly
import com.example.medix.presentation.view.screens.app.appointment.AppointmentsViewModel
import com.example.medix.presentation.view.screens.app.DoctorsViewModel
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DoctorAppointmentsScreen(
    appointmentsViewModel : AppointmentsViewModel = hiltViewModel(),
    doctorsViewModel: DoctorsViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val doctorId = doctorsViewModel.doctor.value?.id
    val appointmentsState by appointmentsViewModel.doctorAppointmentsState.collectAsState()
    val deleteState by appointmentsViewModel.deleteAppointmentState.collectAsState()

    LaunchedEffect(doctorId, deleteState) {
        if (doctorId != null) {
            appointmentsViewModel.getDoctorAppointments(doctorId)
        }
    }

    LaunchedEffect(deleteState) {
        when (deleteState) {
            is Resource.Success -> {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(
                        context,
                        "Appointment Cancelled Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            is Resource.Failure -> {

            }

            Resource.Loading -> {

            }
        }
    }

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
            TopBarTitleOnly(
                title = "Appointments"
            )
        }

        when (val result = appointmentsState) {
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Loading...")
                }
            }

            is Resource.Success -> {
                val appointments = result.data
                LazyColumn(
                    modifier = Modifier
                        .padding(20.dp, top = 20.dp, end = 20.dp, bottom = 0.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1),
                    contentPadding = PaddingValues(all = Dimens.extraSmallPadding2)
                ) {
                    items(appointments) { appointment ->
                        DoctorAppointmentCard(appointment = appointment)
                    }
                }
            }

            is Resource.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "You Haven't Booked Any Appointments Yet!")
                }
            }
        }
    }
}