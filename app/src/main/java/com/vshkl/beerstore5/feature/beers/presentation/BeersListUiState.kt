package com.vshkl.beerstore5.feature.beers.presentation

import com.vshkl.beerstore5.feature.beers.Beer

data class BeersListUiState(
    val state: UiState = UiState.Idle,
    val beers: List<Beer> = listOf(),
    val page: Int = 0,
    val endReached: Boolean = false,
    val error: String = "",
)

sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data object Refreshing : UiState()
    data object Data : UiState()
    data object Error : UiState()
}
