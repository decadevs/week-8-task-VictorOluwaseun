package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class GenerationVi(
    @Json(name = "omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @Json(name = "x-y")
    val xY: XY
)