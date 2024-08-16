package com.alaishat.mohammad.domain.usecase

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.repo.SearchResultRepo

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class GetSearchResultUseCase(private val searchResultRepo: SearchResultRepo) {
    suspend operator fun invoke(searchQuery: String): MealsListDomainModel = searchResultRepo.getSearchResultOn(searchQuery)
}