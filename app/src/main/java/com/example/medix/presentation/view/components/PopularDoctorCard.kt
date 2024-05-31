package com.example.medix.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.medix.domain.model.Doctor
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.lightMixture

@Composable
fun PopularDoctorCard(
    doctor: Doctor,
    onClick: (Doctor) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(12.dp)
            .size(120.dp, 140.dp)
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .background(lightMixture)
            .clickable { onClick(doctor) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = doctor.image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(30.dp))
        )
        doctor.name?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularDoctorCardPreview(){
    MedixTheme {
        PopularDoctorCard(
            doctor = Doctor(
                id = 1,
                speciality = "Dentist",
                bio = "he is the best around here",
                name = "Abdelrahman Tarif",
                address = "",
                phone = "0123456789",
                dateOfBirth = "12/12/2023",
                gender = "Male",
                email = "",
                image = "",
                wage = 0.0,
                favorites = 0,
                appointments = 0,
                imagefile = ""
            ),
            onClick = {}
        )
    }
}