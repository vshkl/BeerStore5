package com.vshkl.beerstore5.feature

import io.ktor.resources.Resource

@Resource("beers")
class BeersResource(
    val page: Int? = null,
    val perPage: Int? = null,
) {

    @Resource("{id}")
    class Id(
        val parent: BeersResource = BeersResource(),
        val id: Int,
    )
}
