package com.example.medix.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medix.R
import com.example.medix.presentation.Dimens
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorBottomNavigationBar(
    items : List<BottomNavigationItem>,
    selected : Int,
    onItemClick : (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = mixture,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selected
            val iconRes = if (isSelected) item.selectedIcon else item.icon
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )

                        Spacer(modifier = Modifier.height(Dimens.extraSmallPadding2))

                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = lightMixture
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorBottomNavigationBarPreview() {
    MedixTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DoctorBottomNavigationBar(
                items = listOf(
                    BottomNavigationItem(
                        icon = R.drawable.appointments_icon,
                        selectedIcon = R.drawable.appointments_icon_filled,
                        text = "Appointments"
                    ),
                    BottomNavigationItem(
                        icon = R.drawable.profile_icon,
                        R.drawable.profile_icon_filled,
                        text = "Profile"
                    )
                ),
                selected = 0,
                onItemClick = {}
            )
        }
    }
}