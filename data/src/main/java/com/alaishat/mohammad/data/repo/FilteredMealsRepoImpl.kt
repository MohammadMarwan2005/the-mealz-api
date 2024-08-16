package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.local.daos.SimpleMealsByAreaDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByCategoriesDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByIngredientDao
import com.alaishat.mohammad.data.local.model.SimpleMealByAreaEntityModel
import com.alaishat.mohammad.data.local.model.SimpleMealByCategoryEntityModel
import com.alaishat.mohammad.data.local.model.SimpledMealByIngredientEntityModel
import com.alaishat.mohammad.data.local.model.toMealDomainModel
import com.alaishat.mohammad.data.local.model.toSimpleMealByAreaEntityModel
import com.alaishat.mohammad.data.local.model.toSimpleMealByCategory
import com.alaishat.mohammad.data.local.model.toSimpleMealEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsDomainModel
import com.alaishat.mohammad.domain.repo.FilteredMealsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
class FilteredMealsRepoImpl(
    val apiService: APIService,
    private val simpleMealsByIngredientDao: SimpleMealsByIngredientDao,
    private val simpleMealsByCategoriesDao: SimpleMealsByCategoriesDao,
    private val simpleMealsByAreaDao: SimpleMealsByAreaDao,
) :
    FilteredMealsRepo {
    override suspend fun getFilteredMealsByCategoryFromRemote(category: String): FilteredMealsDomainModel {
        return withContext(Dispatchers.IO) {
            val localResponse: List<SimpleMealByCategoryEntityModel> =
                simpleMealsByCategoriesDao.getFilteredMealsByCategory(category)
            if (localResponse.isNotEmpty()) {
                return@withContext FilteredMealsDomainModel(localResponse.map { it.toMealDomainModel() })
            }
            val remoteResponse: FilteredMealsDomainModel = apiService.getFilteredMealsByCategory(category)
            simpleMealsByCategoriesDao.insertAllCategories(remoteResponse.meals.map { it.toSimpleMealByCategory(category) })
            return@withContext remoteResponse
        }
    }

    override suspend fun getFilteredMealsByIngredientFromRemote(ingredient: String): FilteredMealsDomainModel {
        return withContext(Dispatchers.IO) {
            val localResponse: List<SimpledMealByIngredientEntityModel> =
                simpleMealsByIngredientDao.getFilteredMealsByIngredient(ingredient)
            if (localResponse.isNotEmpty()) {
                return@withContext FilteredMealsDomainModel(localResponse.map { it.toMealDomainModel() })
            }
            val remoteResponse: FilteredMealsDomainModel = apiService.getMealsByIngredient(ingredient)
            simpleMealsByIngredientDao.insertAllSimpleMeals(remoteResponse.meals.map {
                it.toSimpleMealEntityModel(
                    ingredient,
                )
            })
            return@withContext remoteResponse
        }
    }

    override suspend fun getFilteredMealsByAreaFromRemote(area: String): FilteredMealsDomainModel {
        return withContext(Dispatchers.IO) {
            val localResponse: List<SimpleMealByAreaEntityModel> =
                simpleMealsByAreaDao.getFilteredMealsByArea(area)
            if (localResponse.isNotEmpty()) {
                return@withContext FilteredMealsDomainModel(localResponse.map { it.toMealDomainModel() })
            }
            val remoteResponse: FilteredMealsDomainModel = apiService.getMealsByArea(area)
            simpleMealsByAreaDao.insertAll(remoteResponse.meals.map { it.toSimpleMealByAreaEntityModel(area) })
            return@withContext remoteResponse
        }
    }
}