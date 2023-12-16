package com.vshkl.beerstore5.feature.beers.remote

interface BeersService {

    suspend fun getBeers(page: Int, perPage: Int): List<BeerDto>

    suspend fun getBeer(id: Int): BeerDto
}
