package com.alaishat.mohammad.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.MealDomainModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */

const val FILTERED_MEALS_BY_INGREDIENT_TABLE_NAME = "ingredients_meals"

@Entity(tableName = FILTERED_MEALS_BY_INGREDIENT_TABLE_NAME)
data class SimpledMealByIngredientEntityModel(
    @PrimaryKey val mealId: Int,
    val mealStr: String?,
    val mealThump: String?,
    val ingredient: String?,
)

fun SimpledMealByIngredientEntityModel.toMealDomainModel() = MealDomainModel(mealId.toString(), mealStr ?: "", mealThump ?: "")

fun MealDomainModel.toSimpleMealEntityModel(ingredient: String?) = SimpledMealByIngredientEntityModel(idMeal.toInt(), strMeal, strMealThumb, ingredient)
