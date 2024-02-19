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
    val paginationSize: Int = 10

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadPokes(page: Int): Flow<List<Poke>> {
        val offset: Int = page * paginationSize + 1
        val pokeFlow: Flow<List<Poke>> = db.pokeDao().getPokemons(offset, paginationSize).stateIn(GlobalScope)
        val pokes: List<Poke> = pokeFlow.first()
        if (pokes.size < paginationSize) {
            try {
                val ids: List<Int> = (offset until offset + paginationSize).toList()
                for (poke: Poke in pokes) {
                    val pos: Int = ids.indexOf(poke.id)
                    if (pos > -1) ids.drop(pos)
                }
                for (id: Int in ids) {
                    val dto: PokeDTO = RetrofitBuilder.apiService.getPokemon(id)
                    insertPoke(dto.toLocalEntity())
                }
            }
            catch(e: Exception) { /* Probablemente un 404 */ }
            return db.pokeDao().getPokemons(offset, paginationSize).stateIn(GlobalScope)
        }
        return pokeFlow
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadFavorites(): Flow<List<Poke>> {
        return db.pokeDao().getFavedPokemons().stateIn(GlobalScope)
    }

    suspend fun findPoke(pokeName: String): Poke? {
        var poke: Poke? = db.pokeDao().getPokemonByName(pokeName)
        if (poke == null) {
            try {
                val dto: PokeDTO = RetrofitBuilder.apiService.getPokemon(pokeName)
                poke = dto.toLocalEntity()
            }
            catch(e: Exception) { /* Probablemente un 404 */ }
        }
        return poke
    }

    fun favPoke(id: Int) {
        db.pokeDao().favPokemon(id)
    }

    fun unfavPoke(id: Int) {
        db.pokeDao().unfavPokemon(id)
    }

    private fun insertPoke(poke: Poke): Unit {
        db.pokeDao().insertPokemon(poke)
    }
}