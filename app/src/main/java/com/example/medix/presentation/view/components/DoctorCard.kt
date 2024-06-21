package com.example.medix.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.medix.R
import com.example.medix.domain.model.Doctor
import com.example.medix.domain.model.FavoritesRequest
import com.example.medix.domain.model.FavoritesResponse
import com.example.medix.presentation.Dimens
import com.example.medix.presentation.view.screens.app.favourites.FavoritesViewModel
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorCard(
    doctor: Doctor,
    onClick: (Doctor) -> Unit,
    onFavoriteClick: (Doctor) -> Unit,
    isFavoriteInitially: Boolean,
){
    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(isFavoriteInitially) }
    //var rating by remember { mutableDoubleStateOf(0.0) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, shape = RoundedCornerShape(12.dp))
        .background(Color.White)
        .clickable { onClick(doctor) },
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        AsyncImage(modifier = Modifier
            .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
            .size(Dimens.doctorCardSize)
            .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(doctor.image).build()
            , contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                .width(160.dp)
                .height(Dimens.doctorCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            doctor.name?.let {
                Text(text = it,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = blackText,
                    maxLines = 1,
                )
            }

            doctor.speciality?.let {
                Text(text = it,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = mixture
                )
            }

            Spacer(modifier = Modifier.width(Dimens.extraSmallPadding2))

            doctor.wage?.let {
                Text(text = "$it EGP",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = mixture
                )
            }

            /*RatingBar(
                modifier = Modifier
                    .size(25.dp),
                rating = rating,
                starsColor = starsColor
            ){
                rating = it
            }*/
        }

        Icon(
            painter = if(isFavorite) painterResource(id = R.drawable.saved_icon)
            else painterResource(id = R.drawable.not_saved_icon),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 0.dp)
                .clickable {
                    onFavoriteClick(doctor)
                    isFavorite = !isFavorite
                },
            tint = lightMixture
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorCardPreview(){
    MedixTheme {
        DoctorCard(
            doctor = Doctor(
                id = 1,
                speciality = "Dentist",
                bio = "he is the best around here",
                name = "Abdelrahman Tarif",
                address = "",
                phone = "0123456789",
                dateOfBirth = "12/12/2023",
                gender = "Male",
                email = "",
                image = "",
                wage = 0.0,
                favorites = emptyList(),
                appointments = emptyList(),
                imagefile = ""
            ),
            onClick = {},
            onFavoriteClick = {},
            isFavoriteInitially = false
        )
    }
}