package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alaishat.mohammad.data.local.model.AREAS_TABLE_NAME
import com.alaishat.mohammad.data.local.model.AreaEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */

@Dao
interface AreasDao {

    @Query("SELECT * FROM $AREAS_TABLE_NAME")
    fun getAllAreas(): List<AreaEntityModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAreas(areas: List<AreaEntityModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnArea(area: AreaEntityModel)

}