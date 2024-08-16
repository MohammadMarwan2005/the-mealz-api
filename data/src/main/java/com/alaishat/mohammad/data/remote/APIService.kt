package com.alaishat.mohammad.data.remote

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.model.allareas.AllAreasDomainModel
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientDomainModel
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesDomainModel
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */

interface APIService {
    suspend fun getAllCategoriesMeals(): AllCategoriesDomainModel
    suspend fun getAllIngredient(): AllIngredientDomainModel
    suspend fun getAllAreas(): AllAreasDomainModel
    suspend fun getMealById(id: Int): MealsListDomainModel
    suspend fun getFilteredMealsByCategory(category: String): FilteredMealsDomainModel
    suspend fun getMealsByArea(area: String): FilteredMealsDomainModel
    suspend fun getMealsByIngredient(ingredient: String): FilteredMealsDomainModel
    suspend fun getRandomMeal(): MealsListDomainModel
    suspend fun getSearchResultOn(searchQuery: String): MealsListDomainModel
}