package com.vshkl.beerstore5.feature

import org.mobilenativefoundation.store.store5.Store

interface StoreProvider<Key : Any, Output : Any> {

    fun provide(): Store<Key, Output>
}