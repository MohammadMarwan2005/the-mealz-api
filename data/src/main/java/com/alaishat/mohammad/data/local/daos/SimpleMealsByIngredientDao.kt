package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alaishat.mohammad.data.local.model.FILTERED_MEALS_BY_AREA_TABLE_NAME
import com.alaishat.mohammad.data.local.model.FILTERED_MEALS_BY_CATEGORY_TABLE_NAME
import com.alaishat.mohammad.data.local.model.FILTERED_MEALS_BY_INGREDIENT_TABLE_NAME
import com.alaishat.mohammad.data.local.model.SimpledMealByIngredientEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */
@Dao
interface SimpleMealsByIngredientDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSimpleMeals(meals: List<SimpledMealByIngredientEntityModel>)

    @Query("SELECT * FROM $FILTERED_MEALS_BY_INGREDIENT_TABLE_NAME")
    fun getAllSimpleMealByIngredients(): List<SimpledMealByIngredientEntityModel>

    // GetFilteredMealsByIngredient
    @Query("SELECT * FROM $FILTERED_MEALS_BY_INGREDIENT_TABLE_NAME WHERE ingredient = :ingredient")
    fun getFilteredMealsByIngredient(ingredient: String): List<SimpledMealByIngredientEntityModel>




}