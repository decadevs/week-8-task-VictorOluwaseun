package com.decagon.pokemonapp.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.pokemon.util.getName
import com.decagon.pokemonapp.network.PokemonApi
import com.decagon.pokemonapp.network.PokemonProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.decagon.pokemonapp.network.Result


class OverviewViewModel : ViewModel() {

    val title by lazy { this.getName()}

    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /*Images*/
    private val _properties = MutableLiveData<List<Result>>()

    val properties: LiveData<List<Result>>
        get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        Log.i(title, "WE ARE HERE")
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    fun getPokemonProperties(inst: LiveDataCallBack) {
        val liveDataSuccess = MutableLiveData<PokemonProperty>()
        val liveDataError = MutableLiveData<String>()
        PokemonApi.retrofitService.getProperties().enqueue(object : Callback<PokemonProperty> {
            override fun onResponse(
                call: Call<PokemonProperty>,
                response: Response<PokemonProperty>
            ) {
                val resp = response.body()
                liveDataSuccess.postValue(resp)
                inst.getResponse<PokemonProperty>(liveDataSuccess)
            }

            override fun onFailure(call: Call<PokemonProperty>, t: Throwable) {
                Log.i(title, t.localizedMessage!!)
                liveDataError.postValue(t.localizedMessage)
                inst.getResponse<String>(liveDataError)
            }
//            getAbilites
        })
//        viewModelScope.launch {
    }
}

interface LiveDataCallBack {
    fun <T> getResponse(lifeData: LiveData<T>)
}

