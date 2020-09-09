package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class Emerald(
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_shiny")
    val frontShiny: String
)