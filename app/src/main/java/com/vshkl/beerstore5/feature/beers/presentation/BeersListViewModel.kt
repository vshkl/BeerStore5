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
import org.mobilenativefoundation.store.store5.StoreReadResponseOrigin
import org.mobilenativefoundation.store.store5.impl.extensions.fresh

class BeersListViewModel(
    private val beersStore: Store<Int, List<Beer>>,
) : ViewModel() {

    private val _beers: MutableStateFlow<List<Beer>> = MutableStateFlow(listOf())
    val beers: StateFlow<List<Beer>> = _beers.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private var currentKey = 0
    private var endReached = false

    init {
        viewModelScope.launch {
            beersStore.stream(StoreReadRequest.cached(key = currentKey, refresh = currentKey == 0))
                .collect { response ->
                    when (response) {
                        is StoreReadResponse.Loading -> {
                            _loading.value = true
                        }
                        is StoreReadResponse.Data -> {
                            if (response.origin is StoreReadResponseOrigin.Fetcher) {
                                _loading.value = false
                            }
                            _beers.value = response.value
                            endReached = response.value.isEmpty()
                        }
                        is StoreReadResponse.Error -> {
                            if (response.origin is StoreReadResponseOrigin.Fetcher) {
                                _loading.value = false
                            }
                        }
                        is StoreReadResponse.NoNewData -> Unit
                    }
                }
        }
    }

    fun loadMore() {
        if (!endReached) {
            currentKey++
            viewModelScope.launch {
                beersStore.fresh(currentKey)
            }
        }
    }

    fun refresh() {
        currentKey = 0
        endReached = false
        viewModelScope.launch {
            _loading.value = true
            beersStore.fresh(currentKey)
        }
    }
}
