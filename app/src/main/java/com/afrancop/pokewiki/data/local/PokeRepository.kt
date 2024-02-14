package com.afrancop.pokewiki.data.local

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.Flow

class PokeRepository (val appContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(appContext)

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadPokes(): Flow<List<Poke>> {
        return db.pokeDao().getPokemons().stateIn(GlobalScope)
    }

    fun insertPoke(poke: Poke): Unit {
        db.pokeDao().insertPokemon(poke)
    }

    fun deletePoke(poke: Poke): Unit {
        db.pokeDao().deletePokemon(poke)
    }
}