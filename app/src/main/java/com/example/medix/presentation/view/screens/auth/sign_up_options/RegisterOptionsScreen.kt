package com.example.medix.presentation.view.screens.auth.sign_up_options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medix.R
import com.example.medix.presentation.navigation.Screens
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange
import com.example.medix.ui.theme.secondary

@Composable
fun RegisterOptions(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                mixture
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp, 24.dp, 28.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.medixlogo),
                contentDescription = "Logo",
                modifier = Modifier.size(350.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Register As",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nerkooneregular)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 55.sp,
                        color = orange
                    )
                )

            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ){

                Column(modifier = Modifier
                    .clickable {
                               navController.navigate(Screens.PatientRegisterRoute.route)
                    },
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Box(modifier = Modifier
                        .size(110.dp)
                        .shadow(10.dp, shape = RoundedCornerShape(40.dp))
                        .background(secondary)

                    ){
                        Image(
                            painter = painterResource(id = R.drawable.patient),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 80.dp, height = 80.dp)
                                .align(Alignment.Center),
                            )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Patient",
                        fontSize = 28.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.ExtraBold
                        )
                }

                
                Spacer(modifier = Modifier.width(50.dp))


                Column(modifier = Modifier
                    .height(250.dp)
                    .clickable {
                        navController.navigate(Screens.DoctorRegisterRoute.route){
                        }
                    },
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Box(modifier = Modifier
                        .size(110.dp)
                        .shadow(10.dp, shape = RoundedCornerShape(40.dp))
                        .background(secondary)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.doctor),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 80.dp, height = 80.dp)
                                .align(Alignment.Center),
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Doctor",
                        fontSize = 28.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewSignUpOptions(){
    RegisterOptions(rememberNavController())
}