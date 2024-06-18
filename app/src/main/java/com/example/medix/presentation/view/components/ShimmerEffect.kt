package com.example.medix.presentation.view.components

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medix.R
import com.example.medix.presentation.Dimens

fun Modifier.shimmerEffect() = this.then(
    composed {
        val transition = rememberInfiniteTransition(label = "")
        val alpha = transition.animateFloat(
            initialValue = 0.2f, targetValue = 0.9f, animationSpec =
            infiniteRepeatable(animation =
            tween(durationMillis = 1000), repeatMode = RepeatMode.Reverse), label = ""
        ).value
        background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
    }
)

@Composable
fun DoctorCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.
    padding(start = 10.dp)){
        Box(modifier = Modifier
            .size(Dimens.doctorCardSize)
            .clip(MaterialTheme.shapes.medium)
            .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.extraSmallPadding)
                .height(Dimens.doctorCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(horizontal = Dimens.mediumPadding1)
                .clip(shape = MaterialTheme.shapes.extraSmall)
                .shimmerEffect()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(15.dp)
                    .padding(horizontal = Dimens.mediumPadding1)
                    .clip(shape = MaterialTheme.shapes.extraSmall)
                    .shimmerEffect()
                )
            }
        }
    }
}

@Composable
fun PopularDoctorCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Box(modifier
        .padding(start = 8.dp, top = 10.dp, bottom = 4.dp)
        .size(125.dp, 150.dp)
        .clip(MaterialTheme.shapes.medium)
        .shimmerEffect()
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardShimmerEffectPreview(){
    DoctorCardShimmerEffect()
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PopularDoctorCardShimmerEffectPreview(){
    PopularDoctorCardShimmerEffect()
}