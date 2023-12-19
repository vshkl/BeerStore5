@file:OptIn(ExperimentalMaterial3Api::class)

package com.vshkl.beerstore5.feature.beerdetails.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vshkl.beerstore5.feature.beerdetails.BeerDetails
import com.vshkl.beerstore5.feature.core.presentation.UiState
import com.vshkl.beerstore5.feature.destinations.BeerDetailsScreenDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.impl.extensions.get
import timber.log.Timber

@OptIn(ExperimentalStoreApi::class)
class BeerDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val beerDetailsStore: Store<Int, BeerDetails>,
) : ViewModel() {

    var beerDetailsUiState: MutableStateFlow<BeerDetailsUiState> =
        MutableStateFlow(BeerDetailsUiState())
        private set

    init {
        viewModelScope.launch {
            val id = BeerDetailsScreenDestination.argsFrom(savedStateHandle).id

            Timber.i("Loading beer details with id: $id")

            beerDetailsStore.stream(StoreReadRequest.cached(key = id, refresh = true))
                .collect { response ->
                    beerDetailsUiState.value = when (response) {
                        is StoreReadResponse.Loading ->
                            beerDetailsUiState.value.copy(
                                state = UiState.Loading,
                                error = "",
                            )
                        is StoreReadResponse.Data ->
                            beerDetailsUiState.value.copy(
                                state = UiState.Data,
                                beerDetails = response.value,
                            )
                        is StoreReadResponse.Error ->
                            beerDetailsUiState.value.copy(
                                state = UiState.Error,
                                error = response.errorMessageOrNull() ?: "Unknown error",
                            )
                        is StoreReadResponse.NoNewData ->
                            beerDetailsUiState.value.copy(
                                state = UiState.Data,
                                error = "",
                            )
                    }
                }
        }
    }

    fun refresh() {
        Timber.i("Refreshing beer details")

        beerDetailsUiState.value.copy(
            state = UiState.Refreshing,
            error = "",
        ).run {
            beerDetailsUiState.value = this
            viewModelScope.launch {
                beerDetailsStore.clear()
                beerDetailsStore.get(0)
            }
        }
    }
}
