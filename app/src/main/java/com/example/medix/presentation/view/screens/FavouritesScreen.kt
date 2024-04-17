package com.example.medix.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FavouritesScreen(){
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Favourites",
            fontSize = 26.sp)
    }
}

@Preview
@Composable
fun FavouritesScreenPreview(){
    FavouritesScreen()
}