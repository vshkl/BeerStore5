package com.vshkl.beerstore5.feature.beers.remote

import com.vshkl.beerstore5.feature.beers.Beer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeerDto(
    val id: Long,
    val name: String,
    val tagline: String = "",
    @SerialName("image_url") val imageUrl: String = "",
)

fun BeerDto.asBeer() = Beer(
    id = id.toInt(),
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
)
