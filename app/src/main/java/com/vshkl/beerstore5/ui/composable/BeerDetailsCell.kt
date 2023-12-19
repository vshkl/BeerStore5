package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed class BeerDetailsCellStyle {
    data object Horizontal : BeerDetailsCellStyle()
    data object Vertical : BeerDetailsCellStyle()
}

@Composable
fun BeerDetailsCell(
    value: String,
    modifier: Modifier = Modifier,
    style: BeerDetailsCellStyle = BeerDetailsCellStyle.Horizontal,
    label: String? = null,
) {
    @Composable
    fun Inner(
        style: BeerDetailsCellStyle,
    ) {
        val paddingEnd = remember {
            when (style) {
                is BeerDetailsCellStyle.Horizontal -> 8.dp
                else -> 0.dp
            }
        }
        val paddingBottom = remember {
            when (style) {
                is BeerDetailsCellStyle.Vertical -> 4.dp
                else -> 0.dp
            }
        }

        label?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(end = paddingEnd, bottom = paddingBottom),
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )
    }

    Box(modifier = modifier.fillMaxWidth()) {
        when (style) {
            BeerDetailsCellStyle.Horizontal -> {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Inner(style = style)
                }
            }
            BeerDetailsCellStyle.Vertical -> {
                Column {
                    Inner(style = style)
                }
            }
        }
    }
}

@Preview
@Composable
fun BeerDetailsCellPreview_Horizontal() {
    BeerDetailsCell(
        label = "name:",
        value = "Punk IPA 2007 - 2010",
        style = BeerDetailsCellStyle.Horizontal,
    )
}

@Preview
@Composable
fun BeerDetailsCellPreview_Vertical() {
    BeerDetailsCell(
        label = "name:",
        value = "Punk IPA 2007 - 2010",
        style = BeerDetailsCellStyle.Vertical,
    )
}