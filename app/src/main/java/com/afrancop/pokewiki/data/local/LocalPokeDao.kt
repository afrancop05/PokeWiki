package com.afrancop.pokewiki.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPokeDao {

    // Obtener todos los pokemons cacheados
    @Query("SELECT * FROM Poke WHERE id >= :offset LIMIT :limit")
    fun getPokemons(offset: Int, limit: Int): Flow<List<Poke>>

    // Obtener pokemons por nombre para la busqueda
    @Query("SELECT * FROM Poke WHERE name LIKE '%' || :pokemonName || '%'")
    fun getPokemonsByName(pokemonName: String): Flow<List<Poke>>

    // Insertar pokemon favorito
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(poke: Poke)

    // Borrar pokemon favorito
    @Delete
    fun deletePokemon(poke: Poke)


}