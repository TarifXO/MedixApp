package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.R
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.mixture

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 20.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.navigate_up),
                        contentDescription = null
                    )
                }
                //Spacer(modifier = Modifier.weight(.55f))
                Text(
                    text = title,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "323",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        color = mixture
                    ),
                    textAlign = TextAlign.Center
                )


                //Spacer(modifier = Modifier.weight(1f))
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
fun TopBarPreview() {
    MedixTheme {
        TopBar(
            title = "Main",
            onBackClick = {}
        )
    }
}