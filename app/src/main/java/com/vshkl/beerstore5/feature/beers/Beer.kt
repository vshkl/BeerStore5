package com.vshkl.beerstore5.feature.beers

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String = "",
    val imageUrl: String = "",
)
