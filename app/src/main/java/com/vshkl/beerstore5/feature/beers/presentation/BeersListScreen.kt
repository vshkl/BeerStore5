package com.vshkl.beerstore5.feature.beers.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vshkl.beerstore5.R
import com.vshkl.beerstore5.feature.beers.Beer
import com.vshkl.beerstore5.util.viewModel

private val dummyBeersList = listOf(
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

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun BeersListScreen(
    viewModel: BeersListViewModel = viewModel(),
    navigator: DestinationsNavigator,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = R.string.title_beers_list))
                }
            )
        },
    ) { innerPadding ->
        BeersListScreenContent(
            beers = viewModel.beers.collectAsState().value,
            onBeerClick = { beer -> println(beer) },
            modifier = Modifier
                .padding(innerPadding),
        )
    }
}
