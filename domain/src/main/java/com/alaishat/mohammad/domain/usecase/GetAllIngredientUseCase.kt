package com.alaishat.mohammad.domain.usecase

import com.alaishat.mohammad.domain.model.allgredient.AllIngredientDomainModel
import com.alaishat.mohammad.domain.repo.AllIngredientRepo

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class GetAllIngredientUseCase(private val allIngredientRepo: AllIngredientRepo) {
    suspend operator fun invoke(): AllIngredientDomainModel = allIngredientRepo.getIngredientList()
}