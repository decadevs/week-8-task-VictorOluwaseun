package com.decagon.pokemonapp.overview

import android.view.View
import com.decagon.pokemonapp.network.Result

interface RecyclerViewClickListener {
    fun onRecyclerViewClickListener(view: View, pokemon: Result)
}