package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsResponse

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
interface FilteredMealsDomainRepo {
    suspend fun getFilteredMealsByCategoryFromRemote(category: String): FilteredMealsResponse
    suspend fun getFilteredMealsByIngredientFromRemote(ingredient: String): FilteredMealsResponse
    suspend fun getFilteredMealsByAreaFromRemote(area: String): FilteredMealsResponse

}