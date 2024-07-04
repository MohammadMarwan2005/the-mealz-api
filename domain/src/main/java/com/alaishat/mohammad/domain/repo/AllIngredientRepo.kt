package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.allgredient.AllIngredientResponse

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
interface AllIngredientRepo {
    suspend fun getIngredientListFromRemote(): AllIngredientResponse
}