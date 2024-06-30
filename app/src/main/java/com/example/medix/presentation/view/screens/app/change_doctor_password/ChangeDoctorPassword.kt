package com.example.medix.presentation.view.screens.app.change_doctor_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medix.R
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.presentation.view.screens.app.PatientsViewModel
import com.example.medix.presentation.view.screens.auth.AuthViewModel
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange
import com.example.medix.ui.theme.secondary

@Composable
fun ChangeDoctorPassword(
    navigateUp : () -> Unit,
    navController: NavController,
    patientsViewModel: PatientsViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
){
    val user by patientsViewModel.selectedPatient.observeAsState()
    val focusManager = LocalFocusManager.current
    var oldPassword by remember { mutableStateOf("") }
    var oldPasswordVisible by remember { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmNewPassword by remember { mutableStateOf("") }
    var confirmNewPasswordVisible by remember { mutableStateOf(false) }

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
                .height(80.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            TopBar(
                title = "Change Password",
                onBackClick = navigateUp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 3.dp),
                        colorFilter = ColorFilter.tint(
                            color = mixture
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Old Password",
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        ),
                        color = blackText,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                }
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = oldPassword,
                    onValueChange = { oldPassword = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    singleLine = true,
                    visualTransformation =
                    if (oldPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    trailingIcon = {
                        val iconImage =
                            if(oldPasswordVisible) {
                                Icons.Filled.Visibility
                            }else{
                                Icons.Filled.VisibilityOff
                            }
                        val description =
                            if (oldPasswordVisible) {
                                "Hide Password"
                            }else{
                                "Show Password"
                            }
                        IconButton(onClick = { oldPasswordVisible = !oldPasswordVisible }) {
                            Icon(imageVector = iconImage, contentDescription = description)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 3.dp),
                        colorFilter = ColorFilter.tint(
                            color = mixture
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "New Password",
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        ),
                        color = blackText,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                }
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    singleLine = true,
                    visualTransformation =
                    if (newPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    trailingIcon = {
                        val iconImage =
                            if(newPasswordVisible) {
                                Icons.Filled.Visibility
                            }else{
                                Icons.Filled.VisibilityOff
                            }
                        val description =
                            if (newPasswordVisible) {
                                "Hide Password"
                            }else{
                                "Show Password"
                            }
                        IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                            Icon(imageVector = iconImage, contentDescription = description)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            /*Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 3.dp),
                        colorFilter = ColorFilter.tint(
                            color = mixture
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Confirm New Password",
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        ),
                        color = blackText,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                }
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = confirmNewPassword,
                    onValueChange = { confirmNewPassword = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    singleLine = true,
                    visualTransformation =
                    if (confirmNewPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    trailingIcon = {
                        val iconImage =
                            if(confirmNewPasswordVisible) {
                                Icons.Filled.Visibility
                            }else{
                                Icons.Filled.VisibilityOff
                            }
                        val description =
                            if (confirmNewPasswordVisible) {
                                "Hide Password"
                            }else{
                                "Show Password"
                            }
                        IconButton(onClick = { confirmNewPasswordVisible = !confirmNewPasswordVisible }) {
                            Icon(imageVector = iconImage, contentDescription = description)
                        }
                    }
                )
            }*/

            Spacer(modifier = Modifier.height(60.dp))

            ElevatedButton(
                text = "Confirm",
                textSize = 25.sp,
                textColor = Color.White,
                backgroundColor = orange,
                padding = PaddingValues(0.dp),
                onClick = {
                    authViewModel.changePassword(oldPassword, newPassword, user?.email ?: "")
                    navController.navigate(Screens.DoctorProfileRoute.route){
                        popUpTo(Screens.DoctorProfileRoute.route){
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
fun ChangeDoctorPasswordPreview(){
    MedixTheme {
        ChangeDoctorPassword(
            navigateUp = {},
            navController = rememberNavController()
        )
    }
}