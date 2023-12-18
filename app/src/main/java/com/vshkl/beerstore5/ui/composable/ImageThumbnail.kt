package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vshkl.beerstore5.R

@Composable
fun ImageThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
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
            .size(size = 64.dp)
            .aspectRatio(1F),
    )
}

@Preview
@Composable
fun ImageThumbnailPreview() {
    ImageThumbnail(imageUrl = "https://images.punkapi.com/v2/192.png")
}
