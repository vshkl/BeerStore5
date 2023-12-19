package com.vshkl.beerstore5.feature.beers.local

import com.vshkl.beerstore5.feature.beers.Beer

fun BeerListEntity.asBeer() = Beer(
    id = id.toInt(),
    name = name,
    tagline = tagline.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
)
