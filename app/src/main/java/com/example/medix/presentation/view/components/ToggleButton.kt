package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.secondary

@Composable
fun ToggleButton(
    initialValue: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(initialValue) }

    Switch(
        checked = isChecked,
        onCheckedChange = {
            isChecked = it
            onValueChanged(it)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = mixture,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = secondary,
            uncheckedBorderColor = Color.Transparent,
            checkedBorderColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun ToggleButtonPreview() {
    MedixTheme {
        ToggleButton(initialValue = false) {

        }
    }
}
