package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class MoveLearnMethod(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)