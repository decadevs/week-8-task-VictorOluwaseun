package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class IconsX(
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_female")
    val frontFemale: Any
)