package com.example.medix.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.medix.domain.model.Doctor
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture

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
            .background(Color.White)
            .clickable { onClick(doctor) },
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = doctor.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )

        doctor.name?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = blackText
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        doctor.speciality?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = mixture
                ),
                modifier = Modifier.padding(top = 4.dp)
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
                name = "Abdelrahman",
                address = "",
                phone = "0123456789",
                dateOfBirth = "12/12/2023",
                gender = "Male",
                email = "",
                image = "",
                wage = 0.0,
                favorites = emptyList(),
                appointments = emptyList(),
                imagefile = ""
            ),
            onClick = {}
        )
    }
}