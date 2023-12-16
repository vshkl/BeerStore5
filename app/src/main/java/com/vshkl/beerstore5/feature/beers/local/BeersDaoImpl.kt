package com.vshkl.beerstore5.feature.beers.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.vshkl.beerstore5.feature.beers.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class BeersDaoImpl(
    private val beersQueries: BeerListEntityQueries,
) : BeersDao {

    override fun selectAll(): Flow<List<Beer>> {
        return beersQueries
            .selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .mapNotNull { it.map(BeerListEntity::asBeer) }
    }

    override fun insertAll(beers: List<Beer>, page: Int) {
        beersQueries.transaction {
            beers.forEach {
                beersQueries.insert(
                    id = it.id.toLong(),
                    name = it.name,
                    tagline = it.tagline,
                    imageUrl = it.imageUrl,
                    page = page.toLong(),
                )
            }
        }
    }

    override fun delete(page: Int) {
        beersQueries.deleteByPage(page.toLong())
    }

    override fun deleteAll() {
        beersQueries.deleteAll()
    }
}
