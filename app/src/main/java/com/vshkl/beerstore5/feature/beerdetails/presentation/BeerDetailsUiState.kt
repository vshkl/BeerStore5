package com.vshkl.beerstore5.feature.beerdetails.presentation

import com.vshkl.beerstore5.feature.beerdetails.BeerDetails
import com.vshkl.beerstore5.feature.core.presentation.UiState

data class BeerDetailsUiState(
    val state: UiState = UiState.Idle,
    val beerDetails: BeerDetails? = null,
    val error: String = "",
)
