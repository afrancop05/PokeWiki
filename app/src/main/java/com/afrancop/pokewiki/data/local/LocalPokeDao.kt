package com.afrancop.pokewiki.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPokeDao {

    // Obtener todos los pokemons
    @Query("SELECT * FROM Poke")
    fun getPokemons(): Flow<List<Poke>>

    // Insertar pokemon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(poke: Poke)

    // Borrar pokemon
    @Delete
    fun deletePokemon(poke: Poke)

}