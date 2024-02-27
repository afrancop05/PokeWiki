package com.afrancop.pokewiki.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPokeDao {

    // Obtener todos los pokemons cacheados
    @Query("SELECT * FROM Poke WHERE id >= :offset LIMIT :limit")
    fun getPokemons(offset: Int, limit: Int): Flow<List<Poke>>

    @Query("SELECT * FROM Poke WHERE favorite")
    fun getFavedPokemons(): Flow<List<Poke>>

    // Obtener pokemons por nombre para la busqueda
    @Query("SELECT * FROM Poke WHERE name LIKE '%' || :pokemonName || '%'")
    fun getPokemonsByName(pokemonName: String): List<Poke>

    // Obtener pokemon por nombre para la busqueda
    @Query("SELECT * FROM Poke WHERE name LIKE :pokemonName")
    fun getPokemonByName(pokemonName: String): Poke?

    @Query("SELECT * FROM Poke WHERE id = :id")
    fun getPokemonById(id: Int): Poke?

    // Insertar pokemon favorito
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(poke: Poke)

    @Query("UPDATE Poke SET  favorite = 1 WHERE id = :id")
    fun favPokemon(id: Int)

    @Query("UPDATE Poke SET  favorite = 0 WHERE id = :id")
    fun unfavPokemon(id: Int)

}