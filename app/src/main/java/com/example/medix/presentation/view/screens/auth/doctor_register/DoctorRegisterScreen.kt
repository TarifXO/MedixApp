package com.example.medix.presentation.view.screens.auth.doctor_register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange
import com.example.medix.ui.theme.primaryGreen
import com.example.medix.ui.theme.secondary

@Composable
fun DoctorRegisterScreen(navController: NavController){

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
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.medixlogo),
                contentDescription = "Logo",
                modifier = Modifier.size(350.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Healing journey starts here",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.oranienbaumregular)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            ElevatedButton(
                text = "Sign Up",
                textSize = 28.sp,
                textColor = Color.White,
                backgroundColor = orange,
                padding = PaddingValues(0.dp),
                onClick = {
                    navController.navigate(Screens.DoctorSignUpRoute.route)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            ElevatedButton(
                text = "Log In",
                textSize = 28.sp,
                textColor = primaryGreen,
                backgroundColor = secondary,
                padding = PaddingValues(0.dp),
                onClick = {
                    navController.navigate(Screens.DoctorLoginRoute.route)
                }
            )
        }

    }
}

@Preview
@Composable
fun PreviewRegisterScreen(){
    DoctorRegisterScreen(rememberNavController())
}