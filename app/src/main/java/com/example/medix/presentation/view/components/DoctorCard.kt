package com.example.medix.presentation.view.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.medix.domain.model.Doctor
import com.example.medix.presentation.Dimens
import com.example.medix.ui.theme.MedixTheme
import com.example.medix.ui.theme.mixture

@Composable
fun DoctorCard(
    doctor: Doctor,
    onClick: (Doctor) -> Unit
){
    val context = LocalContext.current
    var rating by remember { mutableDoubleStateOf(0.0) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(doctor) }){
        AsyncImage(modifier = Modifier
            .size(Dimens.articleCardSize)
            .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(doctor.urlToImage).build()
            , contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.extraSmallPadding)
                .height(Dimens.articleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = doctor.title,
                style = MaterialTheme.typography.bodyMedium,
                color = mixture,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = doctor.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = mixture
                )

                Spacer(modifier = Modifier.width(Dimens.extraSmallPadding2))

                RatingBar(
                    modifier = Modifier
                        .size(20.dp),
                    rating = rating,
                ){
                    rating = it
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorCardPreview(){
    MedixTheme {
        DoctorCard(
            doctor = Doctor(
                id = 1,
                name = "tefoo",
                description = "",
                title = "Abdelrahman Tarif",
                url = "",
                urlToImage = "",
            ),
            onClick = {}
        )
    }
}