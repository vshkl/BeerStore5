package com.vshkl.beerstore5

import androidx.lifecycle.SavedStateHandle
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.vshkl.beerstore5.feature.beers.BeersStoreProvider
import com.vshkl.beerstore5.feature.beers.local.BeersDaoImpl
import com.vshkl.beerstore5.feature.beers.presentation.BeersListViewModel
import com.vshkl.beerstore5.feature.beers.remote.BeersServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class DependencyContainer(
    private val activity: MainActivity,
) {

    private val httpClient: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Resources)
            install(DefaultRequest) {
                url("https://api.punkapi.com/v2/")
            }
            install(Logging)
        }
    }
    private val database: Database by lazy {
        Database(
            driver = AndroidSqliteDriver(
                Database.Schema,
                activity.applicationContext,
                "beerstore.db",
            )
        )
    }

    private val beersStoreProvider: BeersStoreProvider by lazy {
        BeersStoreProvider(
            beersService = BeersServiceImpl(httpClient),
            beersDao = BeersDaoImpl(database.beerListEntityQueries),
        )
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> createViewModel(modelClass: Class<T>, handle: SavedStateHandle): T {
        return when (modelClass) {
            BeersListViewModel::class.java -> BeersListViewModel(beersStoreProvider.provide())
            else -> throw RuntimeException("Unknown view model $modelClass")
        } as T
    }
}
