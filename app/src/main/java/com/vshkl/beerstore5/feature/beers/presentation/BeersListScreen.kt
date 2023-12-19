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
import com.vshkl.beerstore5.feature.destinations.BeerDetailsScreenDestination
import com.vshkl.beerstore5.util.viewModel

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
            beersListUiState = viewModel.beersListUiState.collectAsState().value,
            onBeerClick = { beer ->
                navigator.navigate(
                    BeerDetailsScreenDestination(
                        id = beer.id,
                        name = beer.name
                    )
                )
            },
            onLoadMore = { viewModel.loadMore() },
            onRefresh = { viewModel.refresh() },
            modifier = Modifier
                .padding(innerPadding),
        )
    }
}
