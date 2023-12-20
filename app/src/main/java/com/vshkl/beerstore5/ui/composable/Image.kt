package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vshkl.beerstore5.R

@Composable
fun Image(
    imageUrl: String,
    modifier: Modifier = Modifier,
    height: Dp = 80.dp,
) {
    val fallbackPainter = rememberVectorPainter(image = Icons.TwoTone.Image)
    val errorPainter = rememberVectorPainter(image = Icons.Rounded.BrokenImage)

    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.cd_beer_image),
        contentScale = ContentScale.Fit,
        placeholder = fallbackPainter,
        error = errorPainter,
        fallback = fallbackPainter,
        modifier = modifier
            .height(height)
            .aspectRatio(ratio = 0.75F, matchHeightConstraintsFirst = true),
    )
}

@Preview
@Composable
fun ImageThumbnailPreview() {
    Image(imageUrl = "https://images.punkapi.com/v2/192.png")
}
