package com.decagon.pokemonapp.model


import com.squareup.moshi.Json

data class VersionGroupDetail(
    @Json(name = "level_learned_at")
    val levelLearnedAt: Int,
    @Json(name = "move_learn_method")
    val moveLearnMethod: MoveLearnMethod,
    @Json(name = "version_group")
    val versionGroup: VersionGroup
)