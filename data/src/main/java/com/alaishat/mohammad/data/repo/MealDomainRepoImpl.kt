package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse
import com.alaishat.mohammad.domain.repo.AllCategoriesDomainRepo

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */
class MealDomainRepoImpl(private val apiService: APIService) : AllCategoriesDomainRepo {
    override suspend fun getAllCategoriesFromRemote(): AllCategoriesResponse = apiService.getAllCategoriesMeals()
}