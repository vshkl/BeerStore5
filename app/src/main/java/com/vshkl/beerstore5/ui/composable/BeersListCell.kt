package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vshkl.beerstore5.feature.beers.Beer

@Composable
fun BeersListCell(
    beer: Beer,
    modifier: Modifier = Modifier,
    onClick: (Beer) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick(beer) },
    ) {
        ImageThumbnail(imageUrl = beer.imageUrl)
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 8.dp),
        ) {
            Text(
                text = beer.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = beer.tagline,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Text(
            text = "#${beer.id}",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun BeersListCellPreview() {
    BeersListCell(
        beer = Beer(
            id = 192,
            name = "Punk IPA 2007 - 2010",
            tagline = "Post Modern Classic. Spiky. Tropical. Hoppy.",
            imageUrl = "https://images.punkapi.com/v2/192.png",
        ),
    )
}
