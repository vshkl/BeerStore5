package com.vshkl.beerstore5.feature.beers.presentation

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
import com.vshkl.beerstore5.feature.beers.Beer
import com.vshkl.beerstore5.ui.composable.BeersList

@Composable
fun BeersListScreenContent(
    beersListUiState: BeersListUiState,
    modifier: Modifier = Modifier,
    onBeerClick: (Beer) -> Unit = {},
    onLoadMore: () -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        Box {
            BeersList(
                beers = beersListUiState.beers,
                refreshing = beersListUiState.state is UiState.Refreshing,
                onCellClick = onBeerClick,
                onLoadMore = onLoadMore,
                onRefresh = onRefresh,
            )

            if (beersListUiState.state is UiState.Loading) {
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
fun BeersListScreenContentPreview() {
    BeersListScreenContent(
        beersListUiState = BeersListUiState(
            state = UiState.Data,
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
    )
}
