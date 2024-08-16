package com.alaishat.mohammad.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.MealDomainModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */


const val FILTERED_MEALS_BY_CATEGORY_TABLE_NAME = "categories_meals"
@Entity(FILTERED_MEALS_BY_CATEGORY_TABLE_NAME)
data class SimpleMealByCategoryEntityModel(
    @PrimaryKey val mealId: Int,
    val mealStr: String?,
    val mealThump: String?,
    val category: String?,
)


fun SimpleMealByCategoryEntityModel.toMealDomainModel() = MealDomainModel(mealId.toString(), mealStr ?: "", mealThump ?: "")

fun MealDomainModel.toSimpleMealByCategory(category: String?) = SimpleMealByCategoryEntityModel(idMeal.toInt(), strMeal, strMealThumb, category)

fun com.alaishat.mohammad.domain.model.MealsList.FullMealDomainModel.toSimpleMealByCategory(category: String?) = SimpleMealByCategoryEntityModel(idMeal.toInt(), strMeal, strMealThumb, category)