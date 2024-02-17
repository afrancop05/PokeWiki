package com.afrancop.pokewiki.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Poke (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val type1: String,
    val type2: String,
    val sprite: String,
    val skill1: String,
    val skill2: String,
    val skill3: String,
    val skill4: String,
    val height: Int,
    val weight: Int,
    val deleted: Boolean = false,
    ) {
        override fun toString(): String =
            "$id,$name,$type1,$type2,$sprite,$skill1,$skill2,$skill3,$skill4,$height,$weight"
    }