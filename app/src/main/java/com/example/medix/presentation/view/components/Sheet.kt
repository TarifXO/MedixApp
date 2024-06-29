package com.example.medix.presentation.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medix.presentation.view.screens.auth.AuthViewModel
import com.example.medix.ui.theme.lightGray
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
    email: String,
    onEmailChange: (String) -> Unit,
    onClose: () -> Unit
) {
    val viewmodel : AuthViewModel = hiltViewModel()
    var isResetPasswordMode by remember { mutableStateOf(false) }
    var isOtpMode by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var resetPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var resetPasswordVisible by remember { mutableStateOf(false) }
    var otp by remember { mutableStateOf("") }

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(),
        onDismissRequest = onClose,
        containerColor = Color.White,

    ) {
        // Sheet content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp))
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                // Check if in verification mode to change title
                val titleText = when {
                        isOtpMode -> "Enter 4 digits code"
                        isResetPasswordMode -> "Set new password"
                        else -> "Forgot password"
                    }
                val infoText = when {
                    isOtpMode -> "Enter the 4 digits code that you received on your email."
                    isResetPasswordMode -> "Set the new password for your account so you can login and access all the features."
                    else -> "Enter your email for the verification process, we will send 4 digits code to your email."
                }

                Text(text = titleText,
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = infoText,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = blackText
                    ),
                )

                Spacer(modifier = Modifier.height(15.dp))

                if (!isOtpMode && !isResetPasswordMode) {
                    TextField(
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        value = email,
                        onValueChange = { newValue ->
                            onEmailChange(newValue)
                            viewmodel.forgotPassword(newValue)
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = blackText
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .border(0.5.dp, lightGray, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions  = KeyboardActions(),
                        placeholder = { Text(text = "Email", color = blackText)},
                    )
                }
                if (isOtpMode) {
                    // Verification code TextFields
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        OTPTextField(
                            text,
                            6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        ) {
                            text = it
                            if (text.length == 6) {
                                focusManager.clearFocus()
                                otp = text
                            }
                        }
                    }
                }
                if (isResetPasswordMode) {
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
                            fontSize = 20.sp
                        ),
                        singleLine = true,
                        visualTransformation =
                        if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()

                            .border(0.5.dp, lightGray, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        placeholder = { Text(text = "New Password", color = blackText)},
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

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        value = resetPassword,
                        onValueChange = { resetPassword = it },
                        textStyle = TextStyle(
                            fontSize = 20.sp
                        ),
                        singleLine = true,
                        visualTransformation =
                        if (resetPasswordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()

                            .border(0.5.dp, lightGray, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        placeholder = { Text(text = "Re-enter Password", color = blackText)},
                        trailingIcon = {
                            val iconImage =
                                if(resetPasswordVisible) {
                                    Icons.Filled.Visibility
                                }else{
                                    Icons.Filled.VisibilityOff
                                }
                            val description =
                                if (resetPasswordVisible) {
                                    "Hide Password"
                                }else{
                                    "Show Password"
                                }
                            IconButton(onClick = { resetPasswordVisible = !resetPasswordVisible }) {
                                Icon(imageVector = iconImage, contentDescription = description)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                    ElevatedButton(
                        text = if (isResetPasswordMode) "Update Password" else "Continue",
                        textSize = 22.sp,
                        textColor = Color.White,
                        backgroundColor = orange,
                        padding = PaddingValues(16.dp,10.dp,16.dp,0.dp),
                        onClick = {
                            if (isOtpMode) {
                                isOtpMode = false
                                isResetPasswordMode = true
                            } else if (isResetPasswordMode) {
                                viewmodel.resetPassword(password, resetPassword, email, otp)
                                onClose()
                            } else {
                                isOtpMode = true
                                isResetPasswordMode = false
                            }
                        }
                    )

            }
        }
    }
}

@Composable
fun OTPTextField(
    value: String,
    length: Int,
    modifier: Modifier = Modifier,
    boxWidth: Dp = 40.dp,
    boxHeight: Dp = 40.dp,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
) {
    val spaceBetweenBoxes = 8.dp
    BasicTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        singleLine = true,
        onValueChange = {
            if (it.length <= length) {
                onValueChange(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    ) {
        Row(
            Modifier
                .size(width = (boxWidth + spaceBetweenBoxes) * length, height = boxHeight),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            repeat(length) { index ->
                val color = if (index == value.length) Color.Black else lightGray
                Box(
                    modifier = Modifier
                        .size(boxWidth, boxHeight)
                        .border(1.dp, color = color, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = value.getOrNull(index)?.toString() ?: "",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = mixture
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SheetPreview() {
    Sheet(
        email = "",
        onEmailChange = {},
        onClose = {}
    )
}