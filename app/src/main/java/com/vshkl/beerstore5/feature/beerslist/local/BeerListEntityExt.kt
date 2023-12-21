package com.vshkl.beerstore5.feature.beerslist.local

import com.vshkl.beerstore5.feature.beerslist.Beer

fun BeerListEntity.asBeer() = Beer(
    id = id.toInt(),
    name = name,
    tagline = tagline.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
)
