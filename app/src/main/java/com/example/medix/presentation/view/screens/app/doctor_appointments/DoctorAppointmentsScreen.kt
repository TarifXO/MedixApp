package com.example.medix.presentation.view.screens.app.doctor_appointments

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medix.domain.model.Patient
import com.example.medix.domain.model.generateFakePagingItemsForPatients
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.view.components.DoctorAppointmentCard
import com.example.medix.presentation.view.components.TopBarTitleOnly
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorAppointmentsScreen(
    patients : List<Patient>,
){
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

        LazyColumn(modifier = Modifier
            .padding(20.dp, top = 20.dp, end = 20.dp, bottom = 0.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1),
            contentPadding = PaddingValues(all = Dimens.extraSmallPadding2)
        ) {
            items(count = patients.lastIndex) {
                DoctorAppointmentCard(patient = patients[it])
            }
        }

    }
}

@Preview
@Composable
fun DoctorAppointmentsScreenPreview(){
    val fakePagingItems = generateFakePagingItemsForPatients(20)
    DoctorAppointmentsScreen(
        patients = fakePagingItems
    )
}