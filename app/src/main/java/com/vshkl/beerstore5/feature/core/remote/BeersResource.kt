package com.vshkl.beerstore5.feature.core.remote

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("beers")
class BeersResource(
    val page: Int? = null,
    @SerialName("per_page") val perPage: Int? = null,
) {

    @Resource("{id}")
    class Id(
        val parent: BeersResource = BeersResource(),
        val id: Int,
    )
}
