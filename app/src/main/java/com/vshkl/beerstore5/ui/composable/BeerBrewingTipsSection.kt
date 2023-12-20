package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BeerBrewingTipsSection(
    tips: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Brewing tips",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Text(
            text = tips,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun BeerBrewingTipsSectionPreview() {
    BeerBrewingTipsSection(
        tips = "While it may surprise you, this version of Punk IPA isn't dry hopped but still " +
                "packs a punch! To make the best of the aroma hops make sure they are fully " +
                "submerged and add them just before knock out for an intense hop hit.",
    )
}
