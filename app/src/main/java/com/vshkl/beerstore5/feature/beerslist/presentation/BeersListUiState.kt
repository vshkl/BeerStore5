package com.vshkl.beerstore5.feature.beerslist.presentation

import com.vshkl.beerstore5.feature.beerslist.Beer
import com.vshkl.beerstore5.feature.core.presentation.UiState

data class BeersListUiState(
    val state: UiState = UiState.Idle,
    val beers: List<Beer> = listOf(),
    val page: Int = 0,
    val endReached: Boolean = false,
    val error: String = "",
)
