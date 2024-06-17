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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.R
import com.example.medix.domain.model.PatientAppointmentsResponse
import com.example.medix.presentation.Dimens.articleCardSize
import com.example.medix.presentation.Dimens.extraSmallPadding2
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange

@Composable
fun PatientAppointmentCard(
    appointment : PatientAppointmentsResponse
){

    Row(modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, shape = RoundedCornerShape(12.dp))
        .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Box(modifier = Modifier
            .padding(0.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
            .size(articleCardSize)
            .clip(MaterialTheme.shapes.medium)
            .background(lightMixture),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = appointment.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = orange,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "FEB",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(0.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                .height(articleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = appointment.doctorName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = blackText,
                maxLines = 1,
            )

            Text(text = appointment.doctorSpeciality,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = mixture
            )

            Spacer(modifier = Modifier.width(extraSmallPadding2))

            Text(text = appointment.time,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = mixture
                )
        }

        Spacer(modifier = Modifier.width(extraSmallPadding2))

        Icon(
            painter = painterResource(id = R.drawable.drop_down_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp),
        )
    }
}

/*@Preview
@Composable
fun PatientAppointmentCardPreview(){
    MedixTheme {
        PatientAppointmentCard(

        )
    }
}*/