package com.vshkl.beerstore5.feature.beerslist

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String = "",
    val imageUrl: String = "",
)
