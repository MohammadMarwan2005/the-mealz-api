package com.alaishat.mohammad.data.remote

import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.model.allareas.AllAreasResponse
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientResponse
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsResponse

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */

interface APIService {
    suspend fun getAllCategoriesMeals(): AllCategoriesResponse
    suspend fun getAllIngredient(): AllIngredientResponse
    suspend fun getAllAreas(): AllAreasResponse
    suspend fun getMealById(id: Int): MealsListResponse
    suspend fun getFilteredMealsByCategory(category: String): FilteredMealsResponse
    suspend fun getMealsByArea(area: String): FilteredMealsResponse
    suspend fun getMealsByIngredient(ingredient: String): FilteredMealsResponse
    suspend fun getRandomMeal(): MealsListResponse
    suspend fun getSearchResultOn(searchQuery: String): MealsListResponse
}