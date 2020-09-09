package com.decagon.pokemonapp.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.decagon.pokemon.util.getName
import com.decagon.pokemonapp.R
import com.decagon.pokemonapp.model.Ability
import com.decagon.pokemonapp.model.Form
import com.decagon.pokemonapp.model.Move
import com.decagon.pokemonapp.model.Pokemon
import com.decagon.pokemonapp.network.Result
import com.decagon.pokemonapp.overview.LiveDataCallBack
import com.decagon.pokemonapp.overview.PHOTO_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_fragment.*

const val PHOTO_URL = "https://pokeres.bastionbot.org/images/pokemon/"

class DetailFragment(private val pokemon: Result) : Fragment() {
    val title: String by lazy { this.getName() }

    private var pokemonDetails: Pokemon? = null
        get() = field
        set(value){
            field = value
        }

    private var abilitesObj: List<Ability>? = null
        get() = field
        set(value) {
            field = value
        }


//    companion object {
//        fun newInstance() = DetailFragment()
//    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val liveDataSuccess = MutableLiveData<Pokemon>()
        val liveDataError = MediatorLiveData<String>()

        tvTitle.text = pokemon.name

        val splitedUrl = pokemon.url.split("/")
        val id = splitedUrl[splitedUrl.size - 2]
        Log.i(title, id)

        Picasso.get().load("$PHOTO_URL/$id.png").into(ivMainPhotoImage)

        viewModel.getPokemonDetails(id, object : LiveDataCallBack {
            override fun <T> getResponse(lifeData: LiveData<T>) {
                lifeData.observe(viewLifecycleOwner, Observer { poke ->
                    val res = poke as Pokemon
                    pokemonDetails = res
//                     viewModel.setAbilitId(pokemonDetails)
                    renderUI(res)
                })
            }

        })

    }

    fun renderUI(data: Pokemon) {
        Log.i(title, "$data")

        loadAll()

        tvSubtitle.text = "Height: " + pokemonDetails!!.height.toString()

        cbAll.setOnCheckedChangeListener { button, bool ->
            when (bool) {
                true -> loadAll()
                false -> {
                    resetAllUI()
                    !isOtherOn() && !bool
//                    if(!isOtherOn) cbAll.isChecked = true
                }
            }
        }

        cbMoves.setOnCheckedChangeListener{
            button, bool->
            when(bool){
                true -> loadMoves()
                false -> {
                    tvMovesSub.text = ""
                    reset()
                    if (!isOtherOn())
                        loadAll()
                }
            }
        }

        cbAbilities.setOnCheckedChangeListener{
            button, bool->
            when(bool){
                true -> loadAbilites()
                false -> {
                    tvAbilitesSub.text = ""
                    reset()
                    if (!isOtherOn())
                        loadAll()
                }
            }
        }

        cbForms.setOnCheckedChangeListener{
            button, bool->
            when(bool){
                true -> loadForms()
                false -> {
                    tvFormsSub.text = ""
                    reset()
                    if (!isOtherOn())
                        loadAll()
                }
            }
        }

        cbIndices.setOnCheckedChangeListener{
            button, bool->
            when(bool){
                true -> loadIndices()
                false -> {
                    tvIndicesSub.text = ""
                    reset()
                    if (!isOtherOn())
                        loadAll()
                }
            }
        }
    }


    private fun reset(){
        isOtherOn() && !cbAll.isChecked
    }

    private fun resetAllUI(){
        tvAbilitesSub.text = ""
        tvFormsSub.text = ""
        tvMovesSub.text = ""
        tvIndicesSub.text = ""
    }


    private fun isOtherOn() = cbAbilities.isChecked || cbMoves.isChecked
                || cbIndices.isChecked || cbForms.isChecked


    private fun loadAll() {
        loadAbilites()
        loadForms()
        loadIndices()
        loadMoves()

        cbAbilities.isChecked = false
        cbForms.isChecked = false
        cbIndices.isChecked = false
        cbMoves.isChecked = false
        cbAll.isChecked = true
    }

    private fun loadMoves() {
        cbAll.isChecked = false
        populateMoves(pokemonDetails!!.moves)
    }

    private fun loadAbilites() {
        cbAll.isChecked = false
        populateAbilites(pokemonDetails!!.abilities)
    }

    private fun loadForms() {
        cbAll.isChecked = false
        populateForms(pokemonDetails!!.forms)
    }

    private fun loadIndices() {
        cbAll.isChecked = false

    }

    private fun populateAbilites(abilites: List<Ability>){
        var str = ""

        for (ability in abilites){
            str += "name "+ ability.ability.name + "\n"
        }

        tvAbilitesSub.text = str
    }

    private fun populateForms(forms: List<Form>){
        var str = ""

        for (form in forms){
            str += "name "+ form.name + "\n"
        }

        tvFormsSub.text = str
    }

    private fun populateMoves(moves: List<Move>){
        var str = ""

        for (move in moves){
            str += "name "+ move.move.name + "\n"
        }

        tvMovesSub.text = str
    }
}
