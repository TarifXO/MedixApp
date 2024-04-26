package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.mixture

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTitleOnly(
    title: String,
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 20.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            titleContentColor = Color.White,
            containerColor = mixture,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Preview
@Composable
fun TopBarTitleOnlyPreview() {
    MedixTheme {
        TopBarTitleOnly(
            title = "Main",
        )
    }
}