package com.alaishat.mohammad.domain.usecase

import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesDomainModel
import com.alaishat.mohammad.domain.repo.AllCategoriesRepo

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */
class GetAllCategoriesUseCase(val allCategoriesRepo: AllCategoriesRepo) {
    suspend operator fun invoke(): AllCategoriesDomainModel = allCategoriesRepo.getAllCategoriesFromRemote()
}
