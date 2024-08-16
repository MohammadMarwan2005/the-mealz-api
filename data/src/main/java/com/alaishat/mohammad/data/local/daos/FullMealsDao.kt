package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.alaishat.mohammad.data.local.model.FULL_MEALS_TABLE_NAME
import com.alaishat.mohammad.data.local.model.FullMealEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */
@Dao
interface FullMealsDao {
    @Query("SELECT * FROM $FULL_MEALS_TABLE_NAME")
    fun getAllMeals(): List<FullMealEntityModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMeals(meals: List<FullMealEntityModel>)

    @Query("SELECT * FROM $FULL_MEALS_TABLE_NAME WHERE idMeal = :mealId")
    fun getFullMealById(mealId: Int): FullMealEntityModel

}