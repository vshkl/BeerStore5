package com.vshkl.beerstore5.feature.core.remote

import com.vshkl.beerstore5.feature.beerdetails.remote.BeerDetailsDto
import com.vshkl.beerstore5.feature.beerslist.remote.BeerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get

class BeersServiceImpl(
    private val client: HttpClient,
) : BeersService {

    override suspend fun getBeers(page: Int, perPage: Int): List<BeerDto> {
        val maybePage = page.takeIf { it > 0 }

        return client
            .get(BeersResource(page = maybePage, perPage))
            .body<List<BeerDto>>()
    }

    override suspend fun getBeerDetails(id: Int): List<BeerDetailsDto> {
        return client
            .get(BeersResource.Id(id = id))
            .body()
    }
}
