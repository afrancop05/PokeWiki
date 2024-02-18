package com.afrancop.pokewiki.data

import android.content.Context
import com.afrancop.pokewiki.data.local.AppDataBase
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.data.remote.PokeDTO
import com.afrancop.pokewiki.data.remote.RetrofitBuilder
import com.afrancop.pokewiki.data.remote.toLocalEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PokeRepository (val appContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(appContext)
    private val paginationSize: Int = 5

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadPokes(page: Int): Flow<List<Poke>> {
        val offset: Int = page * paginationSize
        val pokeFlow: Flow<List<Poke>> = db.pokeDao().getPokemons(offset, paginationSize).stateIn(GlobalScope)
        val pokes: List<Poke> = pokeFlow.first()
        if (pokes.size < paginationSize) {
            try {
                for (i: Int in pokes.size until paginationSize) {
                    val dto: PokeDTO = RetrofitBuilder.apiService.getPokemon(i)
                    db.pokeDao().insertPokemon(dto.toLocalEntity())
                }
            }
            catch(e: Exception) { /* Probablemente un 404 */ }
            return db.pokeDao().getPokemons(offset, paginationSize).stateIn(GlobalScope)
        }
        return pokeFlow
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun searchPokes(pokeName: String): Flow<List<Poke>> {
        return db.pokeDao().getPokemonsByName(pokeName).stateIn(GlobalScope)
    }

    fun insertPoke(poke: Poke): Unit {
        db.pokeDao().insertPokemon(poke)
    }

    fun deletePoke(poke: Poke): Unit {
        db.pokeDao().deletePokemon(poke)
    }
}