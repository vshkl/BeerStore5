package com.vshkl.beerstore5.feature.core.remote

import com.vshkl.beerstore5.feature.beerdetails.remote.BeerDetailsDto
import com.vshkl.beerstore5.feature.beers.remote.BeerDto

interface BeersService {

    companion object {
        const val pageSize = 40
    }

    suspend fun getBeers(page: Int, perPage: Int): List<BeerDto>

    suspend fun getBeerDetails(id: Int): List<BeerDetailsDto>
}
