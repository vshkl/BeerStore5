package com.vshkl.beerstore5.feature.beers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vshkl.beerstore5.feature.beers.Beer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.impl.extensions.fresh

class BeersListViewModel(
    private val beersStore: Store<Int, List<Beer>>,
) : ViewModel() {

    private val _beers: MutableStateFlow<List<Beer>> = MutableStateFlow(listOf())
    val beers: StateFlow<List<Beer>> = _beers.asStateFlow()

    private var currentKey = 0

    init {
        viewModelScope.launch {
            beersStore.stream(StoreReadRequest.cached(key = currentKey, refresh = currentKey == 0))
                .collect { response ->
                    when (response) {
                        is StoreReadResponse.Loading -> {}
                        is StoreReadResponse.Data -> {
                            _beers.value = response.value
                        }
                        is StoreReadResponse.Error -> {}
                        is StoreReadResponse.NoNewData -> Unit
                    }
                }
        }
    }

    fun loadMore() {
        currentKey++
        viewModelScope.launch {
            beersStore.fresh(currentKey)
        }
    }
}
