package com.afrancop.pokewiki.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Poke::class], version = 1)

abstract class AppDataBase : RoomDatabase() {
    abstract fun pokeDao(): LocalPokeDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, AppDataBase::class.java, "pokewiki.sql")
                    .build()
                    .also { Instance = it }

            }
        }
    }
}