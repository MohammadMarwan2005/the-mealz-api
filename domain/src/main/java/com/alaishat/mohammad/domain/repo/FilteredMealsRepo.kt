package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
interface FilteredMealsRepo {
    suspend fun getFilteredMealsByCategoryFromRemote(category: String): FilteredMealsDomainModel
    suspend fun getFilteredMealsByIngredientFromRemote(ingredient: String): FilteredMealsDomainModel
    suspend fun getFilteredMealsByAreaFromRemote(area: String): FilteredMealsDomainModel

}