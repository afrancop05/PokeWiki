package com.afrancop.pokewiki.data.remote

class RemotePokeDataSource(private val apiService: ApiService) {
    suspend fun getPokemon(num:Int,name: String, types: String, abilities: String, sprites: String,height: Int,weight: Int) =
        apiService.getPokemon(num,name, types, abilities, sprites,height,weight)

}