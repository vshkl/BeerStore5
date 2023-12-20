package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vshkl.beerstore5.feature.beerdetails.BeerDetails

@Composable
fun BeerDetailsList(
    beerDetails: BeerDetails,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        item {
            BeerInfoSection(beerDetails = beerDetails)
        }
        item {
            beerDetails.foodPairing
                .takeIf(List<String>::isNotEmpty)
                ?.let { foodPairing ->
                    FoodPairingSection(foodPairing = foodPairing)
                }
        }
        item {
            BeerBrewingTipsSection(tips = beerDetails.brewersTips)
        }
    }
}

@Preview
@Composable
fun BeerDetailsListPreview() {
    BeerDetailsList(
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
    )
}
