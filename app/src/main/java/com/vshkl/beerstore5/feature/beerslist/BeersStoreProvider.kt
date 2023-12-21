package com.vshkl.beerstore5.feature.beerslist

import com.vshkl.beerstore5.feature.beerslist.local.BeersDao
import com.vshkl.beerstore5.feature.beerslist.remote.BeerDto
import com.vshkl.beerstore5.feature.beerslist.remote.asBeer
import com.vshkl.beerstore5.feature.core.StoreProvider
import com.vshkl.beerstore5.feature.core.remote.BeersService
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
                        .getBeers(page = page, perPage = BeersService.pageSize)
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
