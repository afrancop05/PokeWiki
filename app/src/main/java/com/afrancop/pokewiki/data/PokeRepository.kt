package com.afrancop.pokewiki.data

import android.content.Context
import com.afrancop.pokewiki.data.local.AppDataBase
import com.afrancop.pokewiki.data.local.Metrics
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.data.remote.RetrofitBuilder
import com.afrancop.pokewiki.data.remote.toLocalEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class PokeRepository (appContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(appContext)
    val paginationSize: Int = 10

    suspend fun loadPokes(page: Int): List<Poke> {
        val offset: Int = page * paginationSize + 1
        val pokeFlow: Flow<List<Poke>> = db.pokeDao().getPokemons(offset, paginationSize)
        val pokes: List<Poke> = pokeFlow.first()
        val newPokes: MutableList<Poke> = mutableListOf()
        var listId = 0
        for (i: Int in offset until offset + paginationSize) {
            if (listId < pokes.size - 1 && pokes[listId].id == i) {
                newPokes += pokes[listId]

                listId++
            } else {
                try {
                    val newPoke = RetrofitBuilder.apiService.getPokemon(i).toLocalEntity()
                    newPokes += newPoke
                    insertPoke(newPoke)
                } catch (e: Exception) { /* Probablemente un 404 */
                }
            }
        }
        return newPokes
    }

    suspend fun loadFavorites(): List<Poke> {
        return db.pokeDao().getFavedPokemons().first()
    }

    fun findPoke(pokeName: String): List<Poke> {
        return db.pokeDao().getPokemonsByName(pokeName)
    }

    fun favPoke(id: Int): Poke? {
        db.pokeDao().favPokemon(id)
        return db.pokeDao().getPokemonById(id)
    }

    fun unfavPoke(id: Int): Poke? {
        db.pokeDao().unfavPokemon(id)
        return db.pokeDao().getPokemonById(id)
    }

    private fun insertPoke(poke: Poke) {
        db.pokeDao().insertPokemon(poke)
    }
    private val newItem = Metrics( null,"001", "button")
    suspend fun loadMetrics(): Flow<List<Metrics>> {
        db.metricsDao().insertMetric(newItem)
        return db.metricsDao().getAllMetrics()
    }
    suspend fun insertMetrics(metrics: Flow<List<Metrics>>) {
        db.metricsDao().insertMetric(metrics)
    }


}