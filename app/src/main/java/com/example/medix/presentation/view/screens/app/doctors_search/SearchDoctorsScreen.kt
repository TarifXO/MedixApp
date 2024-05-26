package com.example.medix.presentation.view.screens.app.doctors_search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.view.components.DoctorCard
import com.example.medix.ui.theme.lightBackground
import com.example.medix.presentation.view.components.SearchBar
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.mixture

@Composable
fun SearchDoctorsScreen(
    navigateUp : () -> Unit,
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails : (Doctor) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(lightBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(180.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            Column {
                TopBar(
                    title = "Search Doctors",
                    onBackClick = navigateUp
                )

                Spacer(modifier = Modifier.height(45.dp))

                SearchBar(
                    text = state.searchQuery,
                    readOnly = false,
                    onValueChange = {event(SearchEvent.UpdateSearchQuery(it))},
                    onSearch = {event(SearchEvent.SearchDoctors)
                    }
                )

                Spacer(modifier = Modifier.height(55.dp))

                if (state.doctors.isNotEmpty()) {
                    state.doctors.forEach { doctor ->
                        DoctorCard(doctor = doctor, onClick = {})
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                } else {
                    Text(text = "No doctors found")
                }
            }
        }
    }
}