package com.example.medix.presentation.view.screens.app.doctor_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.medix.R
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorDetails(
    navigateUp : () -> Unit,
    navController: NavController,
    doctor: Doctor
){
    val context = LocalContext.current

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
                title = "Doctor Details",
                onBackClick = navigateUp
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                .border(0.10.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
                .background(Color.White)
                .clip(MaterialTheme.shapes.medium)
            ){
                Column {
                    AsyncImage(modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(MaterialTheme.shapes.large),
                        contentScale = ContentScale.Crop,
                        model = ImageRequest.Builder(context).data(doctor.urlToImage).build()
                        , contentDescription = null
                    )

                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))

                        Text(text = doctor.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = blackText,
                            maxLines = 1,
                        )

                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = doctor.name,
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                                color = mixture
                            )
                            Text(text = "400 EGP/hr")
                        }
                    }
                }

                Box(modifier = Modifier
                    .size(60.dp)
                    .padding(top = 20.dp, end = 20.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(100.dp))
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(Color.White)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.not_saved_icon),
                        tint = lightMixture,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(text = "About Me",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = blackText
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "lorem ipsum opsampas n paonspaf pas onap sfasp " +
                        "lmfasfnpai msapmof aljfhasjlf kdna" +
                        " kslna snkn nk knankf lansknf lksna " +
                        "lknaei ifgjsn slne idn ianionr inda idn aino din " +
                        "fain pfind indnf ipan  idnapi irnidpn inpiwne " +
                        "inipns dkabnvkld npi dopjvd.",
                    maxLines = 4,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = blackText
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.address_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Address",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "House 2, EL 7elw, Tanta",
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                color = Color.Black,
                                maxLines = 2
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.phone_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Phone",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "+201147940368",
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                ElevatedButton(
                    text = "Book Appointment",
                    textSize = 20.sp,
                    textColor = Color.White,
                    backgroundColor = mixture,
                    padding = PaddingValues(0.dp),
                    onClick = {
                        navController.navigate(Screens.AppointmentRoute.route)
                    }
                )
            }
        }

    }
}

@Preview
@Composable
fun DoctorDetailsPreview(){
    MedixTheme {
        DoctorDetails(
            navigateUp = {},
            navController = rememberNavController(),
            doctor = Doctor(
                id = 1,
                name = "tefoo",
                description = "",
                title = "Abdelrahman Tarif",
                url = "",
                urlToImage = ""
            )
        )
    }
}