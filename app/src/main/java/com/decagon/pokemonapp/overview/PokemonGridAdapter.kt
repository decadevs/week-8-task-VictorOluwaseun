package com.decagon.pokemonapp.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.pokemonapp.R
import com.decagon.pokemonapp.network.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_view_item.view.*

const val PHOTO_URL ="https://pokeres.bastionbot.org/images/pokemon/"

class PokemonGridAdapter : RecyclerView.Adapter<PokemonGridAdapter.PokemonPropertyViewHolder>() {

//    private var results = mutableListOf<PokemonProperty>()
    private var results = mutableListOf<Result>()
    var listener: RecyclerViewClickListener? = null

   inner class PokemonPropertyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPropertyViewHolder {
        //Todo modify the layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
        return PokemonPropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonPropertyViewHolder, position: Int) {
        val imgUrl = results[position].url.split("/")
        val id = position+1
//        val id = imgUrl[imgUrl.size-2]
        Picasso.get().load("$PHOTO_URL/$id.png").into(holder.itemView.imgPokemon)
        holder.itemView.tvName.text = results[position].name

        holder.itemView.setOnClickListener{
            listener?.onRecyclerViewClickListener(it, results[position])
        }
    }

    override fun getItemCount() = results.count()

    fun setResult(results: List<Result>){
        this.results = results as MutableList<Result>
        notifyDataSetChanged() // So that our results will be displayed by the recycler view
    }

}