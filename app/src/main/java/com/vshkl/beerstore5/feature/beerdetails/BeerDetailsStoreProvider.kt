package com.vshkl.beerstore5.feature.beerdetails

import com.vshkl.beerstore5.feature.beerdetails.local.BeerDetailsDao
import com.vshkl.beerstore5.feature.beerdetails.remote.asBeerDetails
import com.vshkl.beerstore5.feature.core.StoreProvider
import com.vshkl.beerstore5.feature.core.remote.BeersService
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder

class BeerDetailsStoreProvider(
    private val beersService: BeersService,
    private val beerDetailsDao: BeerDetailsDao,
) : StoreProvider<Int, BeerDetails> {

    override fun provide(): Store<Int, BeerDetails> {
        return StoreBuilder
            .from(
                fetcher = Fetcher.of { id: Int ->
                    beersService
                        .getBeerDetails(id = id)
                        .asBeerDetails()
                },
                sourceOfTruth = SourceOfTruth.Companion.of(
                    reader = { id -> beerDetailsDao.selectById(id) },
                    writer = { _, beerDetails -> beerDetailsDao.insert(beerDetails) },
                    delete = { id -> beerDetailsDao.delete(id) },
                    deleteAll = { beerDetailsDao.deleteAll() },
                )
            )
            .build()
    }
}
