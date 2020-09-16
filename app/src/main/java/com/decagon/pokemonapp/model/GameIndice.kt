package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class GameIndice(
    @Json(name = "game_index")
    val gameIndex: Int,
    @Json(name = "version")
    val version: Version
)