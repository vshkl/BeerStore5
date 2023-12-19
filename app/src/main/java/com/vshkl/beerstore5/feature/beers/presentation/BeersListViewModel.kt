package com.vshkl.beerstore5.feature.beers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vshkl.beerstore5.feature.beers.Beer
import com.vshkl.beerstore5.feature.core.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import timber.log.Timber

@OptIn(ExperimentalStoreApi::class)
class BeersListViewModel(
    private val beersStore: Store<Int, List<Beer>>,
) : ViewModel() {

    var beersListUiState: MutableStateFlow<BeersListUiState> = MutableStateFlow(BeersListUiState())
        private set

    init {
        viewModelScope.launch {
            val page = beersListUiState.value.page

            Timber.i("Loading beers list for page: $page")

            beersStore.stream(StoreReadRequest.cached(key = page, refresh = page == 0))
                .collect { response ->
                    beersListUiState.value = when (response) {
                        is StoreReadResponse.Loading ->
                            beersListUiState.value.copy(
                                state = UiState.Loading,
                                error = "",
                            )
                        is StoreReadResponse.Data ->
                            beersListUiState.value.copy(
                                state = UiState.Data,
                                beers = response.value,
                                endReached = response.value.isEmpty(),
                                error = "",
                            )
                        is StoreReadResponse.Error ->
                            beersListUiState.value.copy(
                                state = UiState.Error,
                                error = response.errorMessageOrNull() ?: "Unknown error",
                            )
                        is StoreReadResponse.NoNewData ->
                            beersListUiState.value.copy(
                                state = UiState.Data,
                                endReached = true,
                                error = "",
                            )
                    }
                }
        }
    }

    fun loadMore() {
        val nextPage = beersListUiState.value.page + 1

        Timber.i("Loading beers list next page: $nextPage")

        if (beersListUiState.value.endReached.not()) {
            beersListUiState.value.copy(
                state = UiState.Loading,
                page = nextPage,
                error = "",
            ).run {
                beersListUiState.value = this
                viewModelScope.launch {
                    beersStore.fresh(page)
                }
            }
        }
    }

    fun refresh() {
        Timber.i("Refreshing beers list")

        beersListUiState.value.copy(
            state = UiState.Refreshing,
            page = 0,
            endReached = false,
            error = "",
        ).run {
            beersListUiState.value = this
            viewModelScope.launch {
                beersStore.clear()
                beersStore.fresh(page)
            }
        }
    }
}
