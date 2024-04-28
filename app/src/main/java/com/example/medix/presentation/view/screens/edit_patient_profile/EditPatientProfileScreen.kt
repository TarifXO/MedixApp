package com.example.medix.presentation.view.screens.edit_patient_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medix.R
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture

@Composable
fun EditPatientProfileScreen(
    navigateUp : () -> Unit,
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
                .height(250.dp)
                .background(mixture)
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(
                    title = "Edit Profile",
                    onBackClick = navigateUp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.size(140.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.tefoo),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.Center)
                            .size(130.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.image_icon),
                        contentDescription = null,
                        alignment = Alignment.BottomEnd,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                    )
                }

            }

        }
    }
}

@Preview
@Composable
fun EditProfileScreenPreview(){
    MedixTheme {
        EditPatientProfileScreen(
            navigateUp = {}
        )
    }
}