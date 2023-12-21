package com.vshkl.beerstore5

import androidx.lifecycle.SavedStateHandle
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.vshkl.beerstore5.feature.beerdetails.BeerDetailsStoreProvider
import com.vshkl.beerstore5.feature.beerdetails.local.BeerDetailsDaoImpl
import com.vshkl.beerstore5.feature.beerdetails.local.BeerDetailsEntity
import com.vshkl.beerstore5.feature.beerdetails.presentation.BeerDetailsViewModel
import com.vshkl.beerstore5.feature.beerslist.BeersStoreProvider
import com.vshkl.beerstore5.feature.beerslist.local.BeersDaoImpl
import com.vshkl.beerstore5.feature.beerslist.presentation.BeersListViewModel
import com.vshkl.beerstore5.feature.core.remote.BeersServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

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
                        coerceInputValues = true
                    }
                )
            }
            install(Resources)
            install(DefaultRequest) {
                url("https://api.punkapi.com/v2/")
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }
                level = LogLevel.INFO
                sanitizeHeader { it == HttpHeaders.Authorization }
            }
        }
    }
    private val database: Database by lazy {
        Database(
            driver = AndroidSqliteDriver(
                Database.Schema,
                activity.applicationContext,
                "beerstore.db",
            ),
            beerDetailsEntityAdapter = BeerDetailsEntity.Adapter(
                foodPairingAdapter = object : ColumnAdapter<List<String>, String> {
                    val separator = "|"

                    override fun decode(databaseValue: String): List<String> = when {
                        databaseValue.isEmpty() -> listOf()
                        else -> databaseValue.split(separator)
                    }

                    override fun encode(value: List<String>): String =
                        value.joinToString(separator)
                },
            )
        )
    }

    private val beersStoreProvider: BeersStoreProvider by lazy {
        BeersStoreProvider(
            beersService = BeersServiceImpl(httpClient),
            beersDao = BeersDaoImpl(database.beerListEntityQueries),
        )
    }

    private val beerDetailsStoreProvider: BeerDetailsStoreProvider by lazy {
        BeerDetailsStoreProvider(
            beersService = BeersServiceImpl(httpClient),
            beerDetailsDao = BeerDetailsDaoImpl(database.beerDetailsEntityQueries),
        )
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> createViewModel(modelClass: Class<T>, handle: SavedStateHandle): T {
        return when (modelClass) {
            BeersListViewModel::class.java ->
                BeersListViewModel(beersStoreProvider.provide())
            BeerDetailsViewModel::class.java ->
                BeerDetailsViewModel(handle, beerDetailsStoreProvider.provide())
            else -> throw RuntimeException("Unknown view model $modelClass")
        } as T
    }
}
