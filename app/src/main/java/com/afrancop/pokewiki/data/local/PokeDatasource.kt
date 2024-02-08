package com.afrancop.pokewiki.data.local

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn


class PokeDatasource(applicationContext: Context) {

    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    suspend fun obtenerPokemons(): Flow<List<Poke>> {
        return db.pokeDao().getPokemons().stateIn(GlobalScope)
    }

    fun insertarPokemon(poke: Poke) {
        return db.pokeDao().insertPokemon(poke)
    }

    fun insertarPokemonFav(poke: Poke) {
        db.pokeDao().insertFavPokemon(poke)
    }

    fun borrarPokemonFav(poke: Poke) {
        return db.pokeDao().deleteFavPokemon(poke)
    }
}