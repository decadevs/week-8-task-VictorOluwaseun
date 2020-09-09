package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class GenerationV(
    @Json(name = "black-white")
    val blackWhite: BlackWhite
)