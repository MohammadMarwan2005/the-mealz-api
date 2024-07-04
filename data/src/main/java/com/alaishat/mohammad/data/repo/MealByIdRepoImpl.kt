package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsResponse
import com.alaishat.mohammad.domain.repo.MealByIdRepo

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class MealByIdRepoImpl(private val apiService: APIService) : MealByIdRepo {
    override suspend fun getMealByIdFromRemote(id: Int): MealsListResponse = apiService.getMealById(id)
}