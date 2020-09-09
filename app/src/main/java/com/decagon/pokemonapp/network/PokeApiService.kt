package com.decagon.pokemonapp.network

import com.decagon.pokemonapp.model.Ability
import com.decagon.pokemonapp.model.Pokemon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import  retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/*
* Retrofit builder to create a Retrofit object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/*
* Right now the goal is to get the JSON response string from the web service,
* and you only need one method to do that: getProperties(). */
interface PokemonAPIService {
    @GET("pokemon")
    fun getProperties(): Call<PokemonProperty>
//            Call<PokemonProperty>

    @GET("pokemon/{id}")
    fun getPokemonDetails(@Path("id") pokemon: String): Call<Pokemon>

    @GET("ability/{id}")
    fun getAbilites(@Path("id") ability: String):Call<List<Ability>>

//    @GET("pokemon-form/{id}")


}

object  PokemonApi{
    val retrofitService: PokemonAPIService by lazy {
        retrofit.create(PokemonAPIService::class.java)
    }
}