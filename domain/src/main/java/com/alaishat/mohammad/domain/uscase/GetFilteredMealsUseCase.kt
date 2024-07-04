package com.alaishat.mohammad.domain.uscase

import com.alaishat.mohammad.domain.repo.FilteredMealsDomainRepo

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
class GetFilteredMealsUseCase(val filteredMealsDomainRepo: FilteredMealsDomainRepo) {
    suspend fun getFilteredMealsByCategory(category: String) =
        filteredMealsDomainRepo.getFilteredMealsByCategoryFromRemote(category)
    suspend fun getFilteredMealsByIngredient(ingredient: String) =
        filteredMealsDomainRepo.getFilteredMealsByIngredientFromRemote(ingredient)
    suspend fun getFilteredMealsByArea(area: String) =
        filteredMealsDomainRepo.getFilteredMealsByAreaFromRemote(area)

}