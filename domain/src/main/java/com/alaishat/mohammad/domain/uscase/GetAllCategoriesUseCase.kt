package com.alaishat.mohammad.domain.uscase

import com.alaishat.mohammad.domain.repo.AllCategoriesDomainRepo

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */
class GetAllCategoriesUseCase(val allCategoriesDomainRepo: AllCategoriesDomainRepo) {
    suspend operator fun invoke() = allCategoriesDomainRepo.getAllCategoriesFromRemote()
}
