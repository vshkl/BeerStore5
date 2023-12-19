package com.vshkl.beerstore5.feature.beers

import com.vshkl.beerstore5.feature.core.StoreProvider
import com.vshkl.beerstore5.feature.beers.local.BeersDao
import com.vshkl.beerstore5.feature.beers.remote.BeerDto
import com.vshkl.beerstore5.feature.core.remote.BeersService
import com.vshkl.beerstore5.feature.beers.remote.asBeer
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder

class BeersStoreProvider(
    private val beersService: BeersService,
    private val beersDao: BeersDao,
) : StoreProvider<Int, List<Beer>> {

    override fun provide(): Store<Int, List<Beer>> {
        return StoreBuilder
            .from(
                fetcher = Fetcher.of { page: Int ->
                    beersService
                        .getBeers(page = page, perPage = 40)
                        .map(BeerDto::asBeer)
                },
                sourceOfTruth = SourceOfTruth.of(
                    reader = { beersDao.selectAll() },
                    writer = { page, beers -> beersDao.insertAll(beers, page) },
                    delete = { page -> beersDao.delete(page) },
                    deleteAll = { beersDao.deleteAll() },
                )
            )
            .build()
    }
}
