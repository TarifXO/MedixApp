package com.example.medix.presentation.view.screens.auth.doctor_sign_up

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.RegisterRequest
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.screens.auth.AuthViewModel
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange
import com.example.medix.ui.theme.primaryGreen
import com.example.medix.ui.theme.secondary



@Composable
fun DoctorSignUpScreen(
    viewModel: AuthViewModel?,
    navController: NavController
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val signupFlow = viewModel?.signupFlow?.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                mixture
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp, 24.dp, 28.dp, 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        )
        {
            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 26.sp
                ),
                color = Color.White,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Enter your credentials to continue",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White
                )

            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = com.example.medix.R.drawable.fullname),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(bottom = 3.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Full Name",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            ),
                            color = Color.White,
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
                        value = fullName,
                        onValueChange = { fullName = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = blackText
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = com.example.medix.R.drawable.email),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(bottom = 3.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Email",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            ),
                            color = Color.White,
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
                        value = email,
                        onValueChange = { email = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = blackText
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = com.example.medix.R.drawable.password),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(bottom = 3.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Password",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 22.sp
                            ),
                            color = Color.White,
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
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        visualTransformation =
                        if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = blackText
                        ),
                        trailingIcon = {
                            val iconImage =
                                if(passwordVisible) {
                                    Icons.Filled.Visibility
                                }else{
                                    Icons.Filled.VisibilityOff
                                }
                            val description =
                                if (passwordVisible) {
                                    "Hide Password"
                                }else{
                                    "Show Password"
                                }
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = iconImage, contentDescription = description)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            ElevatedButton(
                text = "Sign Up",
                textSize = 25.sp,
                textColor = primaryGreen,
                backgroundColor = secondary,
                padding = PaddingValues(0.dp),
                onClick = {
                    val registerRequest = RegisterRequest(
                        username = fullName,
                        email = email,
                        password = password,
                        isPatient = false,
                        isDoctor = true,
                        phone = "",
                        dateOfBirth = "",
                        gender = "",
                        speciality = null,
                        bio = null,
                        address = null,
                        wage = null,
                        image = null
                    )
                    viewModel?.signup(registerRequest)
                    navController.navigate(Screens.DoctorLoginRoute.route)
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account?",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = "Log in",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = orange
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.PatientLoginRoute.route)
                    }
                )

            }

            Spacer(modifier = Modifier.height(15.dp))

            /*Row(modifier = Modifier.fillMaxWidth()
                ,verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = com.example.medix.R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.size(width = 130.dp, height = 12.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "OR",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.height(35.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Icon(
                    painter = painterResource(id = com.example.medix.R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.size(width = 130.dp, height = 12.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Sign up with your social account",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = com.example.medix.R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 43.dp),
                    tint = Color.White
                )
                Icon(
                    painter = painterResource(id = com.example.medix.R.drawable.facebook),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 45.dp),
                    tint = Color.White
                )
            }*/
        }

        signupFlow?.value?.let {
            when(it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, "Please enter valid values.", Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Screens.DoctorNavigation.route){
                            popUpTo(Screens.AuthRoute.route)
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun DoctorSignUpScreenPreview() {
    DoctorSignUpScreen(
        viewModel = null,
        rememberNavController()
    )
}