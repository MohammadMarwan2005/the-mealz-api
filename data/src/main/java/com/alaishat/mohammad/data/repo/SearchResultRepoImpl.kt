package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.repo.SearchResultRepo

/**
 * Created by Mohammad Al-Aishat on Jul/01/2024.
 * Mealz App Project.
 */
class SearchResultRepoImpl(private val apiService: APIService): SearchResultRepo {
    override suspend fun getSearchResultOn(searchQuery: String): MealsListResponse = apiService.getSearchResultOn(searchQuery)
}