package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class Other(
    @Json(name = "dream_world")
    val dreamWorld: DreamWorld,
    @Json(name = "official-artwork")
    val officialArtwork: OfficialArtwork
)