package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse

/**
 * Created by Mohammad Al-Aishat on Jun/26/2024.
 * Mealz App Project.
 */
interface AllCategoriesDomainRepo {
    suspend fun getAllCategoriesFromRemote(): AllCategoriesResponse
}

