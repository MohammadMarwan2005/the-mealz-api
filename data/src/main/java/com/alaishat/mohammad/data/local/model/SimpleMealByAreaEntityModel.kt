package com.alaishat.mohammad.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.MealDomainModel

/**
 * Created by Mohammad Al-Aishat on Aug/15/2024.
 * Mealz App Project.
 */

const val FILTERED_MEALS_BY_AREA_TABLE_NAME = "area_meals"

@Entity(FILTERED_MEALS_BY_AREA_TABLE_NAME)
data class SimpleMealByAreaEntityModel(

    @PrimaryKey val mealId: Int,
    val mealStr: String?,
    val mealThump: String?,
    val area: String?,
)
fun SimpleMealByAreaEntityModel.toMealDomainModel() = MealDomainModel(mealId.toString(), mealStr ?: "", mealThump ?: "")

fun MealDomainModel.toSimpleMealByAreaEntityModel(area: String?) = SimpleMealByAreaEntityModel(idMeal.toInt(), strMeal, strMealThumb, area)
