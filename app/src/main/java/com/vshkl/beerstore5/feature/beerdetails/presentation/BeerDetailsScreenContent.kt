package com.vshkl.beerstore5.feature.beerdetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vshkl.beerstore5.feature.beerdetails.BeerDetails
import com.vshkl.beerstore5.feature.core.presentation.UiState
import com.vshkl.beerstore5.ui.composable.BeerDetailsList

@Composable
fun BeerDetailsScreenContent(
    beerDetailsUiState: BeerDetailsUiState,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        Box {
            beerDetailsUiState.beerDetails?.let { beerDetails ->
                BeerDetailsList(beerDetails = beerDetails)
            }

            if (beerDetailsUiState.state is UiState.Loading ||
                beerDetailsUiState.state is UiState.Refreshing
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}

@Preview
@Composable
fun BeerDetailsScreenContentPreview() {
    BeerDetailsScreenContent(
        beerDetailsUiState = BeerDetailsUiState(
            state = UiState.Data,
            beerDetails = BeerDetails(
                id = 192,
                name = "Punk IPA 2007 - 2010",
                tagline = "Post Modern Classic. Spiky. Tropical. Hoppy.",
                description = "Our flagship beer that kick started the craft beer revolution. This " +
                    "is James and Martin's original take on an American IPA, subverted with " +
                    "punchy New Zealand hops. Layered with new world hops to create an all-out " +
                    "riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering " +
                    "bitter finish.",
                imageUrl = "https://images.punkapi.com/v2/192.png",
                firstBrewed = "04/2007",
                abv = 6.0,
                ibu = 60.0,
                ebc = 17.0,
                srm = 8.5,
                ph = 4.4,
                attenuationLevel = 82.14,
                foodPairing = listOf(
                    "Spicy carne asada with a pico de gallo sauce",
                    "Shredded chicken tacos with a mango chilli lime salsa",
                    "Cheesecake with a passion fruit swirl sauce",
                ),
                brewersTips = "While it may surprise you, this version of Punk IPA isn't dry hopped " +
                    "but still packs a punch! To make the best of the aroma hops make sure they " +
                    "are fully submerged and add them just before knock out for an intense hop hit.",
            ),
        ),
    )
}
