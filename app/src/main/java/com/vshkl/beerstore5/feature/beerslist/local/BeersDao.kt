package com.vshkl.beerstore5.feature.beerslist.local

import com.vshkl.beerstore5.feature.beerslist.Beer
import kotlinx.coroutines.flow.Flow

interface BeersDao {

    fun selectAll(): Flow<List<Beer>>

    fun insertAll(beers: List<Beer>, page: Int)

    fun delete(page: Int)

    fun deleteAll()
}
