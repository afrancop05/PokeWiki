package com.afrancop.pokewiki.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPokeDao {

    // Obtener todos los pokemons
    @Query("SELECT * FROM Poke")
    fun getPokemons(): Flow<List<Poke>>

    // Insertar pokemon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(poke: Poke)

    // Insertar pokemon favorito
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavPokemon(poke: Poke)

    // Borrar pokemon favorito
    @Delete
    fun deleteFavPokemon(poke: Poke)

}