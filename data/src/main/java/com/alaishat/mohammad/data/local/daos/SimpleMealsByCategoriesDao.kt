package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alaishat.mohammad.data.local.model.FILTERED_MEALS_BY_CATEGORY_TABLE_NAME
import com.alaishat.mohammad.data.local.model.SimpleMealByCategoryEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */
@Dao
interface SimpleMealsByCategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(meals: List<SimpleMealByCategoryEntityModel>)

    @Query("SELECT * FROM $FILTERED_MEALS_BY_CATEGORY_TABLE_NAME")
    fun getCategories(): List<SimpleMealByCategoryEntityModel>

    @Query("SELECT * FROM $FILTERED_MEALS_BY_CATEGORY_TABLE_NAME WHERE category = :category")
    fun getFilteredMealsByCategory(category: String): List<SimpleMealByCategoryEntityModel>

}