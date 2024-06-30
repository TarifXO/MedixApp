package com.example.medix.presentation.view.screens.app.doctor_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.medix.R
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.navigation.Screens
import com.example.medix.presentation.view.components.ToggleButton
import com.example.medix.presentation.view.components.TopBarTitleOnly
import com.example.medix.presentation.view.screens.app.DoctorsViewModel
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.mixture
import com.example.medix.ui.theme.orange
import com.example.medix.ui.theme.secondary
import kotlinx.coroutines.launch

@Composable
fun DoctorProfileScreen(
    doctorsProfileViewModel: DoctorProfileViewModel = hiltViewModel(),
    doctorsViewModel: DoctorsViewModel = hiltViewModel(),
    navController: NavController,
){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val user by doctorsViewModel.doctor.observeAsState()

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
                .height(80.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            TopBarTitleOnly(
                title = "Profile"
            )
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.edit_profile_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .shadow(5.dp, shape = RoundedCornerShape(100.dp))
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Edit Profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = blackText,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .shadow(8.dp, shape = RoundedCornerShape(12.dp))
                .background(secondary)
                .clickable {
                    navController.navigate(Screens.EditDoctorProfileRoute.route)
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(user?.image)
                        .build(),
                    modifier = Modifier
                        .padding(0.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                        .size(60.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Column(
                    modifier = Modifier
                        .padding(0.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                        .height(80.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    user?.let {
                        Text(
                            text = it.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = blackText,
                            maxLines = 1,
                        )
                    }
                    user?.let {
                        Text(
                            text = it.email,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                                color = Color.Black,
                                lineHeight = 15.sp
                            ),
                            modifier = Modifier.width(180.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(Dimens.extraSmallPadding2))

                }

                Spacer(modifier = Modifier.width(Dimens.extraSmallPadding2))

                Icon(
                    painter = painterResource(id = R.drawable.pen_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 10.dp, end = 5.dp),
                    tint = mixture
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Account Settings",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = blackText,
            )

            Spacer(modifier = Modifier.height(5.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shadow(0.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { },
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Notifications",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = mixture,
                    )

                    ToggleButton(initialValue = false){

                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

            }

            Image(
                painter = painterResource(id = R.drawable.flat_line_icon),
                contentDescription = null,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(mixture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shadow(0.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate(
                            Screens.ChangeDoctorPasswordRoute.route
                        )
                    },
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Change Password",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = mixture,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

            }

            Image(
                painter = painterResource(id = R.drawable.flat_line_icon),
                contentDescription = null,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(mixture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )


            /*Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shadow(0.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { },
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Language",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = mixture,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.flat_line_icon),
                contentDescription = null,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(mixture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )*/

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shadow(0.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { },
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Privacy Policy",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = mixture,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.flat_line_icon),
                contentDescription = null,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(mixture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shadow(0.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { },
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Terms & Conditions",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = mixture,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.flat_line_icon),
                contentDescription = null,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(mixture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, top = 10.dp, bottom = 0.dp, end = 10.dp)
                    .shadow(20.dp, shape = RoundedCornerShape(20.dp))
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = orange, shape = RoundedCornerShape(12.dp))
                    .clickable {
                        coroutineScope.launch {
                            doctorsProfileViewModel.logout()
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Left
                ) {
                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.sign_out_icon),
                        contentDescription = null,
                        modifier = Modifier,
                        alignment = Alignment.CenterStart
                    )

                    Spacer(modifier = Modifier.width(80.dp))
                    Text(
                        text = "Log Out",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        //modifier = Modifier.align(Alignment.Center)
                    )

                }
            }
        }
    }
}