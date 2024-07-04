package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.repo.RandomMealRepo

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class RandomMealRepoImpl(private val apiService: APIService): RandomMealRepo {
    override suspend fun getRandomMeals(): MealsListResponse = apiService.getRandomMeal()
}