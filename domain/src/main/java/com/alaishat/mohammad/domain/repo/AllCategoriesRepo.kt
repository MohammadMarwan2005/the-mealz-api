package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/26/2024.
 * Mealz App Project.
 */
interface AllCategoriesRepo {
    suspend fun getAllCategoriesFromRemote(): AllCategoriesDomainModel
}

