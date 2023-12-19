package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(state = scrollState),
    ) {
        BeerDetailsCell(value = beerDetails.tagline)
        Spacer(modifier = Modifier.height(16.dp))
        BeerDetailsCell(value = beerDetails.description)
        Spacer(modifier = Modifier.height(16.dp))
        BeerDetailsCell(label = "first brew", value = beerDetails.firstBrewed)
        Spacer(modifier = Modifier.height(16.dp))
        BeerDetailsCell(label = "abv", value = beerDetails.abv.toString())
        BeerDetailsCell(label = "ibu", value = beerDetails.ibu.toString())
        BeerDetailsCell(label = "ebc", value = beerDetails.ebc.toString())
        BeerDetailsCell(label = "srm", value = beerDetails.srm.toString())
        BeerDetailsCell(label = "ph", value = beerDetails.ph.toString())
        BeerDetailsCell(
            label = "attenuation level",
            value = beerDetails.attenuationLevel.toString(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        beerDetails.foodPairing
            .takeIf(List<String>::isNotEmpty)
            ?.let { foodPairing ->
                BeerDetailsCell(
                    label = "food pairing",
                    value = foodPairing.joinToString("\n") { "· $it" },
                    style = BeerDetailsCellStyle.Vertical,
                )
            }
        Spacer(modifier = Modifier.height(16.dp))
        BeerDetailsCell(
            label = "brewers tips",
            value = beerDetails.brewersTips,
            style = BeerDetailsCellStyle.Vertical,
        )
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
