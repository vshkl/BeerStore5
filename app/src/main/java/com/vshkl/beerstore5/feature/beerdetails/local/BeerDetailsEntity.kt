package com.vshkl.beerstore5.feature.beerdetails.local

import com.vshkl.beerstore5.feature.beerdetails.BeerDetails

fun BeerDetailsEntity.asBeerDetails() = BeerDetails(
    id = id.toInt(),
    name = name,
    tagline = tagline.orEmpty(),
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    firstBrewed = firstBrewed.orEmpty(),
    abv = abv ?: 0.0,
    ibu = ibu ?: 0.0,
    ebc = ebc ?: 0.0,
    srm = srm ?: 0.0,
    ph = ph ?: 0.0,
    attenuationLevel = attenuationLevel ?: 0.0,
    foodPairing = foodPairing,
    brewersTips = brewersTips.orEmpty(),
)
