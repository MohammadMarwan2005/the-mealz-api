package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
interface RandomMealRepo {
    suspend fun getRandomMeals(): MealsListDomainModel
}