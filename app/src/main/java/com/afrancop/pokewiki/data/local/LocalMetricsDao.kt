package com.afrancop.pokewiki.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalMetricDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMetric(metrics: Metrics)

    @Query("SELECT * FROM Metrics")
    fun getAllMetrics(): List<Metrics>
}