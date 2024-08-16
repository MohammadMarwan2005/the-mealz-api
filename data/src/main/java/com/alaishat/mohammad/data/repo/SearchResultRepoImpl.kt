package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.local.daos.FullMealsDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByAreaDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByCategoriesDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByIngredientDao
import com.alaishat.mohammad.data.local.model.toFullMealDomainModel
import com.alaishat.mohammad.data.local.model.toFullMealEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsList.FullMealDomainModel
import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.repo.SearchResultRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Mohammad Al-Aishat on Jul/01/2024.
 * Mealz App Project.
 */
class SearchResultRepoImpl(
    private val apiService: APIService,
    private val fullMealsDao: FullMealsDao,
    private val simpleMealsByIngredientDao: SimpleMealsByIngredientDao,
    private val simpleMealsByCategoriesDao: SimpleMealsByCategoriesDao,
    private val simpleMealsByAreaDao: SimpleMealsByAreaDao,
) : SearchResultRepo {
    override suspend fun getSearchResultOn(searchQuery: String): MealsListDomainModel {
        return withContext(Dispatchers.IO) {
            try {
                val remoteResponse = apiService.getSearchResultOn(searchQuery)
                fullMealsDao.insertAllMeals(remoteResponse.meals.map { it.toFullMealEntityModel() })
                return@withContext remoteResponse
            } catch (e: Exception) {
                val fullMealsList: List<FullMealDomainModel> =
                    fullMealsDao.getAllMeals().map { it.toFullMealDomainModel() }.filter { it.isAccepted(searchQuery) }

                return@withContext MealsListDomainModel(fullMealsList)
            }
        }
    }
}

fun FullMealDomainModel.isAccepted(query: String): Boolean = this.strMeal.contains(query, ignoreCase = true)