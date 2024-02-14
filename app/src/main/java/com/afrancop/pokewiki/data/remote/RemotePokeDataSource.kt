package com.afrancop.pokewiki.data.remote

class RemotePokeDataSource(private val apiService: ApiService) {
    suspend fun getPokemon(id:Int) =
        apiService.getPokemon(id)

}