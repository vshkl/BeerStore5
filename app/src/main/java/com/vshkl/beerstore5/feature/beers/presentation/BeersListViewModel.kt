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

class BeersListViewModel(
    private val beersStore: Store<Int, List<Beer>>,
) : ViewModel() {

    private val _beers: MutableStateFlow<List<Beer>> = MutableStateFlow(listOf())
    val beers: StateFlow<List<Beer>> = _beers.asStateFlow()

    init {
        viewModelScope.launch {
            beersStore.stream(StoreReadRequest.cached(key = 1, refresh = true))
                .collect { response ->
                    println(response)
                    when (response) {
                        is StoreReadResponse.Loading -> {}
                        is StoreReadResponse.Data -> {
                            _beers.value = response.value
                        }
                        is StoreReadResponse.Error.Exception -> {}
                        is StoreReadResponse.Error.Message -> {}
                        is StoreReadResponse.NoNewData -> {}
                    }
                }
        }
    }
}
