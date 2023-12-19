package com.vshkl.beerstore5.feature.beerdetails.local

import com.vshkl.beerstore5.feature.beerdetails.BeerDetails
import kotlinx.coroutines.flow.Flow

interface BeerDetailsDao {

    fun selectById(id: Int): Flow<BeerDetails?>

    fun insert(beerDetails: BeerDetails)

    fun delete(id: Int)

    fun deleteAll()
}
