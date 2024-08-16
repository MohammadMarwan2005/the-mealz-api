package com.alaishat.mohammad.domain.usecase

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.repo.RandomMealRepo

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */
class GetRandomMealUseCase(private val randomMealsRepo: RandomMealRepo) {
    suspend operator fun invoke(): MealsListDomainModel = randomMealsRepo.getRandomMeals()
}