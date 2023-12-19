package com.vshkl.beerstore5.feature.beerdetails.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import com.vshkl.beerstore5.feature.beer.local.BeerDetailsEntity
import com.vshkl.beerstore5.feature.beer.local.BeerDetailsEntityQueries
import com.vshkl.beerstore5.feature.beerdetails.BeerDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BeerDetailsDaoImpl(
    private val beerDetailsQueries: BeerDetailsEntityQueries,
) : BeerDetailsDao {

    override fun selectById(id: Int): Flow<BeerDetails?> {
        return beerDetailsQueries
            .selectById(id.toLong())
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .map(BeerDetailsEntity::asBeerDetails)
    }

    override fun insert(beerDetails: BeerDetails) {
        beerDetailsQueries.insert(
            id = beerDetails.id.toLong(),
            name = beerDetails.name,
            tagline = beerDetails.tagline,
            description = beerDetails.description,
            imageUrl = beerDetails.imageUrl,
            firstBrewed = beerDetails.firstBrewed,
            abv = beerDetails.abv,
            ibu = beerDetails.ibu,
            ebc = beerDetails.ebc,
            srm = beerDetails.srm,
            ph = beerDetails.ph,
            attenuationLevel = beerDetails.attenuationLevel,
            foodPairing = beerDetails.foodPairing,
            brewersTips = beerDetails.brewersTips,
        )
    }

    override fun delete(id: Int) {
        beerDetailsQueries.deleteById(id.toLong())
    }

    override fun deleteAll() {
        beerDetailsQueries.deleteAll()
    }
}
