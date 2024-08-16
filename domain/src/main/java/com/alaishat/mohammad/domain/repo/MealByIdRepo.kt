package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
interface MealByIdRepo {
    suspend fun getMealByIdFromRemote(id: Int): MealsListDomainModel
}