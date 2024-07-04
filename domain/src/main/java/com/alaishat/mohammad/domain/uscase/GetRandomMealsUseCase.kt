package com.alaishat.mohammad.domain.uscase

import com.alaishat.mohammad.domain.repo.RandomMealRepo

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class GetRandomMealUseCase(private val randomMealsUseCase: RandomMealRepo) {
    suspend operator fun invoke() = randomMealsUseCase.getRandomMeals()
}