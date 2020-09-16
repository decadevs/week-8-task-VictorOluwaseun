package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class DreamWorld(
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_female")
    val frontFemale: Any
)