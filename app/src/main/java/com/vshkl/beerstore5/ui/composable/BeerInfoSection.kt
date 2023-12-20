package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vshkl.beerstore5.feature.beerdetails.BeerDetails

@Composable
fun BeerInfoSection(
    beerDetails: BeerDetails,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = beerDetails.tagline,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.shapes.medium,
                    )
                    .border(
                        width = Dp.Hairline,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .padding(8.dp),
            ) {
                Image(
                    imageUrl = beerDetails.imageUrl,
                    height = 160.dp,
                )
            }
            BeerParameters(
                abv = beerDetails.abv,
                ibu = beerDetails.ibu,
                ebc = beerDetails.ebc,
                srm = beerDetails.srm,
                ph = beerDetails.ph,
                attenuationLevel = beerDetails.attenuationLevel,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
        Text(
            text = beerDetails.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = "First brewed at ${beerDetails.firstBrewed}",
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview
@Composable
fun BeerInfoSectionPreview() {
    BeerInfoSection(
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