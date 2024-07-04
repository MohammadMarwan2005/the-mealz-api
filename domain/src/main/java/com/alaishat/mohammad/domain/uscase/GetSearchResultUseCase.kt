package com.alaishat.mohammad.domain.uscase

import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.repo.SearchResultRepo

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class GetSearchResultUseCase(private val searchResultRepo: SearchResultRepo) {
    suspend operator fun invoke(searchQuery: String): MealsListResponse = searchResultRepo.getSearchResultOn(searchQuery)
}