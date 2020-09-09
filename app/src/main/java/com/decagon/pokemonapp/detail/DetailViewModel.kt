package com.decagon.pokemonapp.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.pokemon.util.getName
import com.decagon.pokemonapp.model.Ability
import com.decagon.pokemonapp.model.Pokemon
import com.decagon.pokemonapp.network.PokemonApi
import com.decagon.pokemonapp.overview.LiveDataCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    val title by lazy { this.getName()}

    private var abilityId = ""
        get() = field
        set(value) {
            field = value
        }

    fun setAbilitId(value: String){
        abilityId = value
    }

    fun getPokemonDetails(id: String, inst: LiveDataCallBack){
        val liveDataSuccess = MutableLiveData<Pokemon>()
        val liveDataError = MutableLiveData<String>()

        PokemonApi.retrofitService.getPokemonDetails(id).enqueue(
            object : Callback<Pokemon>{
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    val resp = response.body()
                    liveDataSuccess.postValue(resp)
                    inst.getResponse<Pokemon>(liveDataSuccess)
                    Log.i(title, "resp $resp")

                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.i(title, t.localizedMessage!!)
                    liveDataError.postValue(t.localizedMessage)
                    inst.getResponse<String>(liveDataError)
                    Log.i(title, "err $liveDataError")

                }

            }
        )
    }

}