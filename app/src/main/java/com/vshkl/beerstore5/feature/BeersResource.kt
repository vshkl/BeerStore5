package com.vshkl.beerstore5.feature

import io.ktor.resources.Resource

@Resource("/beers")
class BeersResource(
    val page: Int = 0,
    val perPage: Int = 20,
) {

    @Resource("{id}")
    class Id(
        val parent: BeersResource = BeersResource(),
        val id: Int,
    )
}
