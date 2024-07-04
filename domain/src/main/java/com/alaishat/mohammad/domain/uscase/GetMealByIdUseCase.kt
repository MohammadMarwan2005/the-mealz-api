package com.alaishat.mohammad.domain.uscase

import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.repo.MealByIdRepo

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class GetMealByIdUseCase(private val mealByIdRepo: MealByIdRepo) {
    suspend operator fun invoke(id: Int): MealsListResponse = mealByIdRepo.getMealByIdFromRemote(id)
}