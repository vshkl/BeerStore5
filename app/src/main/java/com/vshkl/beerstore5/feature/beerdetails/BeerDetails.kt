package com.vshkl.beerstore5.feature.beerdetails

data class BeerDetails(
    val id: Int,
    val name: String,
    val tagline: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val firstBrewed: String,
    val abv: Double = 0.0,
    val ibu: Double = 0.0,
    val ebc: Double = 0.0,
    val srm: Double = 0.0,
    val ph: Double = 0.0,
    val attenuationLevel: Double = 0.0,
    val foodPairing: List<String> = listOf(),
    val brewersTips: String = "",
)
