package com.decagon.pokemonapp.network

data class PokemonProperty(
    val count: Int,
    val next: String?,
    val previous: String?,
    var imgUrl: String?,
    val results: List<Result>
)

data class Result(
    val name: String,
    val url: String,
    var imgUrl: String?
)