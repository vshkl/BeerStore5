package com.vshkl.beerstore5.feature.beers.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vshkl.beerstore5.feature.beers.Beer
import com.vshkl.beerstore5.ui.composable.BeersList

@Composable
fun BeersListScreenContent(
    beers: List<Beer>,
    modifier: Modifier = Modifier,
    onBeerClick: (Beer) -> Unit = {},
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        BeersList(
            beers = beers,
            onCellClick = onBeerClick,
        )
    }
}

@Preview
@Composable
fun BeersListScreenContentPreview() {
    BeersListScreenContent(
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
