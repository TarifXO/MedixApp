package com.example.medix.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.ui.theme.primaryGreen
import com.example.medix.ui.theme.secondary

@Composable
fun ElevatedButton(text: String,
                   textSize : TextUnit,
                   textColor: Color,
                   backgroundColor: Color,
                   padding : PaddingValues,
                   onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = textSize,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun ElevatedButtonPreview(){
    ElevatedButton(
        text = "Sign Up",
        textSize = 25.sp,
        textColor = primaryGreen,
        padding = PaddingValues(28.dp),
        backgroundColor = secondary
    ) {
        
    }
}
