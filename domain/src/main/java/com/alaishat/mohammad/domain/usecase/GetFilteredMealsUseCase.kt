package com.alaishat.mohammad.domain.usecase

import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsDomainModel
import com.alaishat.mohammad.domain.repo.FilteredMealsRepo

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
class GetFilteredMealsUseCase(val filteredMealsRepo: FilteredMealsRepo) {
    suspend fun getFilteredMealsByCategory(category: String): FilteredMealsDomainModel =
        filteredMealsRepo.getFilteredMealsByCategoryFromRemote(category)
    suspend fun getFilteredMealsByIngredient(ingredient: String): FilteredMealsDomainModel =
        filteredMealsRepo.getFilteredMealsByIngredientFromRemote(ingredient)
    suspend fun getFilteredMealsByArea(area: String): FilteredMealsDomainModel =
        filteredMealsRepo.getFilteredMealsByAreaFromRemote(area)

}