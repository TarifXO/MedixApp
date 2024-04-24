package com.example.medix.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.medix.ui.theme.lightMixture
import kotlinx.coroutines.delay

@Composable
fun CustomLayout(
    icon: Painter,
    text: String
) {
    var animatedVisibility by remember { mutableStateOf(false) }
    var generatedText by remember { mutableStateOf("") }

            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .shadow(22.dp, shape = RoundedCornerShape(100.dp))
                    .clip(CircleShape)
            )

            Box(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .heightIn(min = 48.dp)
                    .shadow(
                        8.dp, shape = RoundedCornerShape(
                            topStart = 0.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp
                        )
                    )
                    .background(lightMixture)
            ) {
                Text(
                    text = generatedText,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterStart)
                )
            }

        Spacer(modifier = Modifier.height(8.dp))

    LaunchedEffect(text) {
        if (!animatedVisibility) {
            animatedVisibility = true
            text.forEachIndexed { index, _ ->
                delay(100)
                generatedText = text.substring(0, index + 1)
            }
        }
    }
}
