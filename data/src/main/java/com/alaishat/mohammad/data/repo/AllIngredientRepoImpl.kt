package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientResponse
import com.alaishat.mohammad.domain.repo.AllIngredientRepo

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class AllIngredientRepoImpl(val apiService: APIService): AllIngredientRepo {
    override suspend fun getIngredientListFromRemote(): AllIngredientResponse = apiService.getAllIngredient()
}