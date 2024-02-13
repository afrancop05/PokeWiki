package com.afrancop.pokewiki.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{num}")
    suspend fun getPokemon(
        @Path("num") num: Int,
        @Query("name") name: String,
        @Query("types") types: String,
        @Query("abilities") abilities: String,
        @Query("sprites") sprites: String,
        @Query("height") height: Int,
        @Query("weight") weight: Int,
    ):PokeDTO

}

