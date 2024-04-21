package com.example.medix.presentation.view.screens.appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medix.presentation.view.components.DaySelection
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun AppointmentScreen(
    navigateUp : () -> Unit,
    navController: NavController
) {
    var patientName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

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
                .background(mixture)
        ) {
            Column {
                TopBar(
                    title = "Appointment",
                    onBackClick = navigateUp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = "Appointment For",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = blackText
            )

            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = patientName,
                onValueChange = { patientName = it },
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp, mixture, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                placeholder = {
                    Text(text = "Patient Name", style = MaterialTheme.typography.bodyLarge,
                        color = mixture
                    )
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = contactNumber,
                onValueChange = { contactNumber = it },
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp, mixture, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                placeholder = {
                    Text(text = "Contact Number", style = MaterialTheme.typography.bodyLarge,
                        color = mixture
                    )
                }
            )



        }

        DaySelection({})

    }
}

@Preview
@Composable
fun AppointmentScreenPreview(){
    MedixTheme {
        AppointmentScreen(
            navigateUp = {},
            navController = rememberNavController()
        )
    }
}