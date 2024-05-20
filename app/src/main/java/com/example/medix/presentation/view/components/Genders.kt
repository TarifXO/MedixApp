package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.domain.model.Gender
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.orange


@Composable
fun GenderSelection(onGenderSelected: (Gender) -> Unit) {
    var selectedGender by remember { mutableStateOf<Gender?>(null) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioGroup(selectedGender) { gender ->
            selectedGender = gender
            onGenderSelected(gender)
        }
    }
}

@Composable
fun RadioGroup(selectedGender: Gender?, onGenderSelected: (Gender) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RadioButton(
            selected = selectedGender == Gender.Male,
            onClick = { onGenderSelected(Gender.Male) },
            colors = RadioButtonColors(
                unselectedColor = lightBackground,
                selectedColor = orange,
                disabledSelectedColor = orange,
                disabledUnselectedColor = lightBackground
            )
        )

        Text(
            text = "Male",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = blackText
        )

        Spacer(modifier = Modifier.width(30.dp))

        RadioButton(
            selected = selectedGender == Gender.Female,
            onClick = { onGenderSelected(Gender.Female) },
            colors = RadioButtonColors(
                unselectedColor = lightBackground,
                selectedColor = orange,
                disabledSelectedColor = orange,
                disabledUnselectedColor = lightBackground
            )
        )

        Text(
            text = "Female",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = blackText
        )
    }
}
