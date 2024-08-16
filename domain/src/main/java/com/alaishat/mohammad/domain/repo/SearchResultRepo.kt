package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
interface SearchResultRepo {
    suspend fun getSearchResultOn(searchQuery: String): MealsListDomainModel
}