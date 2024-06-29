package com.example.medix.presentation.view.screens.auth.doctor_log_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.medix.R
import com.example.medix.data.authentication.Resource
import com.example.medix.domain.model.LogInRequest
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.components.Sheet
import com.example.medix.presentation.view.screens.auth.AuthViewModel
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange
import com.example.medix.ui.theme.primaryGreen
import com.example.medix.ui.theme.secondary

@Composable
fun DoctorLogInScreen(
    viewModel: AuthViewModel?,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var emailCheck by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val loginFlow = viewModel?.doctorLoginFlow?.collectAsState()

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
                text = "Log In",
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
                            painter = painterResource(id = R.drawable.email),
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
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = blackText
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
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
                            painter = painterResource(id = R.drawable.password),
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
                        value = password,
                        onValueChange = { password = it },
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = blackText
                        ),
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
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
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
                                Icon(
                                    imageVector = iconImage,
                                    contentDescription = description,
                                    tint = blackText
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))


            ElevatedButton(
                text = "Log In",
                textSize = 25.sp,
                textColor = primaryGreen,
                backgroundColor = secondary,
                padding = PaddingValues(0.dp),
                onClick = {
                    val loginRequest = LogInRequest(
                        email = email,
                        password = password,
                    )
                    viewModel?.doctorLogin(loginRequest)
                    /*navController.navigate(Screens.MedixNavigation.route){
                        popUpTo(Screens.AuthRoute.route)
                    }*/
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Forgot password?",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.clickable{
                        showBottomSheet = true
                    }
                )
            }

            // Show the bottom sheet if showBottomSheet is true
            if (showBottomSheet) {
                Sheet(
                    email = emailCheck,
                    onEmailChange = { emailCheck = it },
                    onClose = { showBottomSheet = false }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don’t have an account?",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = "Sign up",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = orange
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.DoctorSignUpRoute.route)
                    }
                )

            }

            Spacer(modifier = Modifier.height(25.dp))

            /*Row(modifier = Modifier.fillMaxWidth()
                ,verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.line),
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
                    painter = painterResource(id = R.drawable.line),
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
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 43.dp),
                    tint = Color.White
                )


                Icon(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 45.dp),
                    tint = Color.White
                )

            }*/
        }

        loginFlow?.value?.let {
            when (it) {
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
                        navController.navigate(Screens.DoctorNavigation.route) {
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
fun PreviewLogInScreen() {
    DoctorLogInScreen(
        viewModel = null,
        rememberNavController()
    )
}