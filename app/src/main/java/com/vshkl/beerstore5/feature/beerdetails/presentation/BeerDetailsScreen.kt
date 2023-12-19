package com.vshkl.beerstore5.feature.beerdetails.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.vshkl.beerstore5.util.viewModel

@ExperimentalMaterial3Api
@Destination(navArgsDelegate = BeerDetailsScreenNavArgs::class)
@Composable
fun BeerDetailsScreen(
    navArgs: BeerDetailsScreenNavArgs,
    viewModel: BeerDetailsViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(navArgs.name)
                }
            )
        },
    ) { innerPadding ->
        BeerDetailsScreenContent(
            beerDetailsUiState = viewModel.beerDetailsUiState.collectAsState().value,
            onRefresh = { viewModel.refresh() },
            modifier = Modifier
                .padding(innerPadding),
        )
    }
}
