package com.vshkl.beerstore5.feature.core.remote

import com.vshkl.beerstore5.feature.beerdetails.remote.BeerDetailsDto
import com.vshkl.beerstore5.feature.beers.remote.BeerDto

interface BeersService {

    suspend fun getBeers(page: Int, perPage: Int): List<BeerDto>

    suspend fun getBeerDetails(id: Int): List<BeerDetailsDto>
}
