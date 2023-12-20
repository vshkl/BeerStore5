package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BeerParametersRow(
    label: String,
    value: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun BeerParametersRowPreview() {
    BeerParametersRow(
        label = "ABV",
        value = 5.0,
    )
}
