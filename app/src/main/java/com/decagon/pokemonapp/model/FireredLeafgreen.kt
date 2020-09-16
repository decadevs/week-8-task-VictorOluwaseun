package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class FireredLeafgreen(
    @Json(name = "back_default")
    val backDefault: String,
    @Json(name = "back_shiny")
    val backShiny: String,
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_shiny")
    val frontShiny: String
)