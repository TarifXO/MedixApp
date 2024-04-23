package com.example.medix.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.datesColor
import com.example.medix.ui.theme.lightMixture


@Composable
fun DayHourSelection(
    dates: List<String>,
    currentSelectedDate: String,
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit,
    onHourSelected: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
            .fillMaxWidth()
            ,
        contentPadding = PaddingValues(0.dp)
    ) {
        items(dates) { date ->
            HourSelection(
                title = date,
                currentToggleState = currentSelectedDate,
                onClickButton = {
                    onDateSelected(date)
                    onHourSelected(it)
                }
            )

        }
    }
}


@Composable
fun HourSelection(
    title: String,
    currentToggleState: String,
    onClickButton: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .size(width = 120.dp, height = 40.dp)
            .shadow(3.dp, shape = RoundedCornerShape(10.dp))
            .clickable { onClickButton(title) }
            .background(
                color = if (title != currentToggleState) datesColor else lightMixture,
                shape = RoundedCornerShape(5.dp)
            )

    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (title != currentToggleState) lightMixture else Color.White
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun HourSelectionPreview(){
    MedixTheme {
        HourSelection(title = "Your Title",
            currentToggleState = "Current State",
            onClickButton = {}
        )
    }
}