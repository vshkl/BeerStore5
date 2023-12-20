package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed

@Composable
fun FoodPairingInfo(
    foodPairing: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Food pairing",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        foodPairing.fastForEachIndexed { index, food ->
            Text(
                text = food,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(bottom = if (index == foodPairing.size - 1) 0.dp else 4.dp),
            )
        }
    }
}

@Preview
@Composable
fun FoodPairingInfoPreview() {
    FoodPairingInfo(
        foodPairing = listOf(
            "Spicy carne asada with a pico de gallo sauce",
            "Shredded chicken tacos with a mango chilli lime salsa",
            "Cheesecake with a passion fruit swirl sauce",
        ),
    )
}
