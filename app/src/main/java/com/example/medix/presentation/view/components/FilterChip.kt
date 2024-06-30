package com.example.medix.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medix.R
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipWithSubItems(
    chipLabel: String,
    onSortOptionSelected: (String) -> Unit,
) {
    var isSelected by remember { mutableStateOf(false) }
    var showSubList by remember { mutableStateOf(false) }
    var filterName by remember { mutableStateOf("") }
    val chipItems = listOf("Default", "Alphabet", "Wage")

    ExposedDropdownMenuBox(
        modifier = Modifier,
        expanded = showSubList,
        onExpandedChange = { showSubList = !showSubList }
    ) {
        FilterChip(
            modifier = Modifier
                .menuAnchor()
                .shadow(12.dp, shape = MaterialTheme.shapes.large)
                .size(width = 90.dp, height = 30.dp),
            selected = false,
            onClick = {
                isSelected = true
            },
            label = {
                Text(
                    text = filterName.ifEmpty { chipLabel },
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = blackText
                    ),
                )
            },
            trailingIcon = {
                Image(
                    modifier = Modifier.rotate(if (showSubList) 180f else 0f),
                    painter = painterResource(id = R.drawable.sort_icon),
                    contentDescription = "List",
                )
            },
            colors = FilterChipDefaults.filterChipColors(containerColor = orange),
        )
        ExposedDropdownMenu(
            expanded = showSubList,
            onDismissRequest = { showSubList = false },
        ) {
            chipItems.forEach { subListItem ->
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        filterName = subListItem
                        showSubList = false
                        onSortOptionSelected(subListItem)
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (subListItem == filterName || subListItem == chipLabel) {
                            lightBackground
                        } else {
                            lightBackground
                        }
                    )
                ) {
                    Text(text = subListItem, color = Color.Black)
                }
            }
        }
    }
}