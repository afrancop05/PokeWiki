package com.afrancop.pokewiki.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Metrics(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val itemId: String,
    val itemType: String
){
    override fun toString(): String =
        "$id,$itemId,$itemType"
}

