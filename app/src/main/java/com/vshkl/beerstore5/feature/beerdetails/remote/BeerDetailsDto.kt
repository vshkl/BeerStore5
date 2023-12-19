package com.vshkl.beerstore5.feature.beerdetails.remote

import com.vshkl.beerstore5.feature.beerdetails.BeerDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeerDetailsDto(
    val id: Int,
    val name: String,
    val tagline: String = "",
    val description: String = "",
    val imageUrl: String = "",
    @SerialName("first_brewed") val firstBrewed: String,
    val abv: Double = 0.0,
    val ibu: Double = 0.0,
    val ebc: Double = 0.0,
    val srm: Double = 0.0,
    val ph: Double = 0.0,
    @SerialName("attenuation_level") val attenuationLevel: Double = 0.0,
    @SerialName("food_pairing") val foodPairing: List<String> = listOf(),
    @SerialName("brewers_tips") val brewersTips: String = "",
)

fun BeerDetailsDto.asBeerDetails() = BeerDetails(
    id = id,
    name = name,
    tagline = tagline,
    description = description,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv,
    ibu = ibu,
    ebc = ebc,
    srm = srm,
    ph = ph,
    attenuationLevel = attenuationLevel,
    foodPairing = foodPairing,
    brewersTips = brewersTips,
)
