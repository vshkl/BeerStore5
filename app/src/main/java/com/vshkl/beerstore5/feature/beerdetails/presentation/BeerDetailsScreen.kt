package com.vshkl.beerstore5.feature.beerdetails.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vshkl.beerstore5.ui.modifier.edgeGradientOverlay
import com.vshkl.beerstore5.util.viewModel

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Destination(navArgsDelegate = BeerDetailsScreenNavArgs::class)
@Composable
fun BeerDetailsScreen(
    navArgs: BeerDetailsScreenNavArgs,
    navigator: DestinationsNavigator,
    viewModel: BeerDetailsViewModel = viewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .edgeGradientOverlay(
                                width = 16.dp,
                                colors = listOf(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    Color.Transparent,
                                ),
                            )
                    ) {
                        Text(
                            text = navArgs.name,
                            maxLines = 1,
                            modifier = Modifier
                                .basicMarquee(
                                    iterations = Int.MAX_VALUE,
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    velocity = 45.dp,
                                )
                                .padding(horizontal = 12.dp),
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to beers list"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.refresh() }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh beer details",
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        BeerDetailsScreenContent(
            beerDetailsUiState = viewModel.beerDetailsUiState.collectAsState().value,
            modifier = Modifier
                .padding(innerPadding),
        )
    }
}
