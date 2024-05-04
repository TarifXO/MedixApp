package com.example.medix.presentation.view.screens.app.edit_patient_profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.medix.R
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.components.Gender
import com.example.medix.presentation.view.components.GenderSelection
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.secondary
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPatientProfileScreen(
    navigateUp : () -> Unit,
    navController : NavController
){
    val focusManager = LocalFocusManager.current
    var name by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val calendarState = rememberSheetState()
    var isCalendarDialogVisible by remember { mutableStateOf(false) }


    fun handleImageSelection(uri: Uri) {
        selectedImageUri = uri
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                handleImageSelection(uri)
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(220.dp)
                .background(mixture)
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(
                    title = "Edit Profile",
                    onBackClick = navigateUp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.size(130.dp)){
                    selectedImageUri?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .padding(bottom = 16.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.image_icon),
                        contentDescription = null,
                        alignment = Alignment.BottomEnd,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .clickable { photoPickerLauncher.launch(PickVisualMediaRequest()) }
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Personal Information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = blackText
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = blackText,
                    unfocusedTextColor = blackText
                ),
                value = name,
                onValueChange = { name = it },
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.pen_icon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(blackText)
                    )
                },
                placeholder = {
                    Text(text = "Name", style = MaterialTheme.typography.bodyLarge,
                        color = mixture
                    )
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = contactNumber,
                onValueChange = { contactNumber = it },
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.pen_icon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(blackText)
                    )
                },
                placeholder = {
                    Text(text = "Contact Number", style = MaterialTheme.typography.bodyLarge,
                        color = mixture
                    )
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable {
                        isCalendarDialogVisible = true
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = dateOfBirth.takeIf { it.isNotEmpty() } ?: "Date Of Birth",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (dateOfBirth.isNotEmpty()) blackText else mixture
                )

                // Trailing icon
                Image(
                    painter = painterResource(id = R.drawable.pen_icon),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .padding(end = 12.dp),
                    colorFilter = ColorFilter.tint(color = blackText)
                )
            }


            if (isCalendarDialogVisible) {
                CalendarDialog(
                    state = calendarState,
                    config = CalendarConfig(
                        monthSelection = true,
                        yearSelection = true,
                        disabledDates = listOf(LocalDate.now().plusDays(7)),
                    ),
                    selection = CalendarSelection.Date {
                        dateOfBirth = it.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    }
                )
            } else {
                // Otherwise, show an empty composable
                Spacer(modifier = Modifier.height(0.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Gender",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = blackText
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                GenderSelection { gender ->
                    when (gender) {
                        Gender.MALE -> {

                        }
                        Gender.FEMALE -> {

                        }
                    }
                }
            }

            ElevatedButton(
                text = "Confirm",
                textSize = 20.sp,
                textColor = Color.White,
                backgroundColor = mixture,
                padding = PaddingValues(10.dp, top = 25.dp, bottom = 25.dp, end = 10.dp),
                onClick = {
                    navController.navigate(
                        Screens.ProfileRoute.route
                    ){
                        popUpTo(Screens.ProfileRoute.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}

@Preview
@Composable
fun EditProfileScreenPreview(){
    MedixTheme {
        EditPatientProfileScreen(
            navigateUp = {},
             navController = rememberNavController()
        )
    }
}