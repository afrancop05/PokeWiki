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
    val type3: String,
    val sprite: String,
    val skill1: String,
    val skill2: String,
    val skill3: String,
    val hideSkill: Boolean,
    val height: Int,
    val weight: Int
    ) {
        override fun toString(): String =
            "${name},${type1},${type2},${type3},${sprite},${skill1},${skill2},${skill3},${hideSkill},${height},${weight}"
    }