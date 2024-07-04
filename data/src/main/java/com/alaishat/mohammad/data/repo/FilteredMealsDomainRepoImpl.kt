package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsResponse
import com.alaishat.mohammad.domain.repo.FilteredMealsDomainRepo

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
class FilteredMealsDomainRepoImpl(val apiService: APIService): FilteredMealsDomainRepo {
    override suspend fun getFilteredMealsByCategoryFromRemote(category: String): FilteredMealsResponse = apiService.getFilteredMealsByCategory(category)
    override suspend fun getFilteredMealsByIngredientFromRemote(ingredient: String): FilteredMealsResponse = apiService.getMealsByIngredient(ingredient)
    override suspend fun getFilteredMealsByAreaFromRemote(area: String): FilteredMealsResponse = apiService.getMealsByArea(area)
}