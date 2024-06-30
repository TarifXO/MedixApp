package com.example.medix.presentation.view.screens.app.medix_model

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.medix.R
import com.example.medix.presentation.view.components.CustomLayout
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.presentation.view.screens.app.MedixAiViewModel
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.secondary

@Composable
fun MedixModelScreen(
    navigateUp : () -> Unit,
    viewModel: MedixAiViewModel = hiltViewModel(),
){
    val iconPainter = painterResource(id = R.drawable.model_ai_icon)
    val imageUrl = viewModel.imageUrl.value
    val result = viewModel.result.value

    Log.d("MedixModelScreen", "imageUrl: $imageUrl, result: $result")
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
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            TopBar(
                title = "Medix Model",
                onBackClick = {
                    viewModel.resetData()
                    navigateUp()
                }
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
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp, 150.dp)
                        .shadow(0.dp, shape = RoundedCornerShape(12.dp))
                        .align(Alignment.Center)
                )
            }

            CustomLayout(
                icon = iconPainter,
                text = result
            )
        }
    }
}

/*@Preview
@Composable
fun MedixModelPreview(){
    MedixTheme {
        MedixModelScreen(
            navigateUp = {},
            viewModel : MedixAiViewModezl()
        )
    }
}*/
