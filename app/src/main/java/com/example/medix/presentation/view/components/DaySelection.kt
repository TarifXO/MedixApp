package com.example.medix.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.datesColor
import com.example.medix.ui.theme.orange
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DaySelection(
    onTitleSelected : (String) -> Unit
    ) {

    val dateList = generateDateList()
    var selectedItem by remember {
        mutableStateOf(dateList.first())
    }

    DisposableEffect(Unit) {
        onTitleSelected(selectedItem)
        onDispose { }
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ){

        items(dateList){ it ->
            CustomToggleButtonList(
                title = it,
                currentToggleState = selectedItem,
                modifier = Modifier.padding(end = 7.dp)
            ){
                selectedItem = it
                onTitleSelected(it)
            }
        }
    }
}

@Composable
fun CustomToggleButtonList(
    title: String,
    currentToggleState: String,
    modifier: Modifier = Modifier,
    onClickButton: (String) -> Unit
) {

    Button(
        modifier = modifier.size(width = 130.dp, height = 60.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = datesColor,
            disabledContainerColor = orange,
            contentColor = Color.Black,
            disabledContentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(pressedElevation = 8.dp, focusedElevation = 8.dp, defaultElevation = 4.dp), // Use elevation only when enabled
        enabled = title != currentToggleState,
        onClick = { onClickButton(title) }
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight(400),
            )
        )
    }
}

fun generateDateList(): List<String> {
    val dateList = mutableListOf<String>()
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMM")
    for (i in 0 until 7) {
        val date = LocalDate.now().plusDays(i.toLong())
        val formattedDate = date.format(formatter)
        dateList.add(formattedDate)
    }

    return dateList
}

@Preview(showBackground = true)
@Composable
fun CustomToggleButtonListPreview(){
    MedixTheme {
        CustomToggleButtonList(title = "Your Title",
            currentToggleState = "Current State",
            onClickButton = {}
        )
    }
}