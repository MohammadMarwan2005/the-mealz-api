package com.alaishat.mohammad.domain.usecase

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.repo.MealByIdRepo

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class GetMealByIdUseCase(private val mealByIdRepo: MealByIdRepo) {
    suspend operator fun invoke(id: Int): MealsListDomainModel = mealByIdRepo.getMealByIdFromRemote(id)
}