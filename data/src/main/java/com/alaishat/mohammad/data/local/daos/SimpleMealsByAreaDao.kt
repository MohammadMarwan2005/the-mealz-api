package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alaishat.mohammad.data.local.model.FILTERED_MEALS_BY_AREA_TABLE_NAME
import com.alaishat.mohammad.data.local.model.SimpleMealByAreaEntityModel
import com.alaishat.mohammad.data.local.model.SimpledMealByIngredientEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */
@Dao
interface SimpleMealsByAreaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(meals: List<SimpleMealByAreaEntityModel>)

    @Query("SELECT * FROM $FILTERED_MEALS_BY_AREA_TABLE_NAME")
    fun getAllFilteredMealsByArea(): List<SimpledMealByIngredientEntityModel>

    @Query("SELECT * FROM $FILTERED_MEALS_BY_AREA_TABLE_NAME WHERE area = :area")
    fun getFilteredMealsByArea(area: String): List<SimpleMealByAreaEntityModel>

}