package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vshkl.beerstore5.feature.beers.Beer

@Composable
fun BeersList(
    beers: List<Beer>,
    modifier: Modifier = Modifier,
    onCellClick: (Beer) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(
            items = beers,
            key = Beer::id,
        ) { beer ->
            BeersListCell(
                beer = beer,
                onClick = onCellClick,
            )
        }
    }
}

@Preview
@Composable
fun BeersListPreview() {
    BeersList(
        beers = listOf(
            Beer(
                id = 191,
                name = "Interstellar",
                tagline = "2013 Prototype Red Rye IPA.",
                imageUrl = "https://images.punkapi.com/v2/191.png",
            ),
            Beer(
                id = 192,
                name = "Punk IPA 2007 - 2010",
                tagline = "Post Modern Classic. Spiky. Tropical. Hoppy.",
                imageUrl = "https://images.punkapi.com/v2/192.png",
            ),
            Beer(
                id = 193,
                name = "Blitz Berliner Weisse",
                tagline = "Berliner Fruit Beer.",
                imageUrl = "https://images.punkapi.com/v2/keg.png",
            ),
        )
    )
}
