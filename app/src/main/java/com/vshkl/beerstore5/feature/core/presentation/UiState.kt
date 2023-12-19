package com.vshkl.beerstore5.feature.core.presentation

sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data object Refreshing : UiState()
    data object Data : UiState()
    data object Error : UiState()
}
