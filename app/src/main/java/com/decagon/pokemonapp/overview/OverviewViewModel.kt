package com.decagon.pokemonapp.overview

import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.pokemon.util.getName
import com.decagon.pokemonapp.network.NetworkStatusChecker
import com.decagon.pokemonapp.network.PokemonApi
import com.decagon.pokemonapp.network.PokemonProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.decagon.pokemonapp.network.Result


class OverviewViewModel : ViewModel() {

//    private val networkStatusChecker by lazy {
//        // This will fetch the connectivity manager use for checking the network info from the system
//        NetworkStatusChecker(getSystemService(ConnectivityManager::class.java))
//    }


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
                try {
                    Log.i(title, t.localizedMessage!!)
                    liveDataError.postValue(t.localizedMessage)
                    inst.getResponse<String>(liveDataError)

                }catch (err: Exception){
                    Log.i(title, "$t")
                }

            }
//            getAbilites
        })
//        viewModelScope.launch {
    }
}

interface LiveDataCallBack {
    fun <T> getResponse(lifeData: LiveData<T>)
}

