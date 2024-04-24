package com.example.medix.presentation.view.screens.medix_model

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.medix.R
import com.example.medix.presentation.view.components.CustomLayout
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.secondary

@Composable
fun MedixModel(
    navigateUp : () -> Unit,
    selectedImageUri: Uri?
){
    val iconPainter = painterResource(id = R.drawable.model_ai_icon)
    val text = "Your generated text here saio labs i epibsja oibe fklb aslb ib al bjkfl abkl fb ibakls bfl k labslkfb  slafh l sasf ..."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(80.dp)
                .background(mixture)
        ) {
            TopBar(
                title = "Medix Model",
                onBackClick = navigateUp
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.patient_ai_icon),
                contentDescription = null,
                modifier = Modifier
                    .shadow(22.dp, shape = RoundedCornerShape(100.dp))
                    .clip(CircleShape)
                    .align(Alignment.End)
            )

            Box(modifier = Modifier
                .padding(end = 30.dp)
                .size(160.dp, 180.dp)
                .shadow(
                    8.dp, shape = RoundedCornerShape(
                        topStart = 20.dp, topEnd = 0.dp, bottomStart = 20.dp, bottomEnd = 20.dp
                    )
                )
                .background(lightMixture)
                .align(Alignment.End)
            ){
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp, 150.dp)
                        .shadow(0.dp, shape = RoundedCornerShape(12.dp))
                        .align(Alignment.Center)
                )
            }

            CustomLayout(
                icon = iconPainter,
                text = text
            )
        }
    }
}

@Preview
@Composable
fun MedixModelPreview(){
    MedixTheme {
        MedixModel(
            navigateUp = {},
            selectedImageUri = null
        )
    }
}
