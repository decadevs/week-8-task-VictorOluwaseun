package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class GenerationIii(
    @Json(name = "emerald")
    val emerald: Emerald,
    @Json(name = "firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @Json(name = "ruby-sapphire")
    val rubySapphire: RubySapphire
)