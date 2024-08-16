package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.local.daos.FullMealsDao
import com.alaishat.mohammad.data.local.model.FullMealEntityModel
import com.alaishat.mohammad.data.local.model.toFullMealDomainModel
import com.alaishat.mohammad.data.local.model.toFullMealEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.repo.MealByIdRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class MealByIdRepoImpl(private val apiService: APIService, private val fullMealsDao: FullMealsDao) : MealByIdRepo {
    override suspend fun getMealByIdFromRemote(id: Int): MealsListDomainModel {
        return withContext(Dispatchers.IO) {
            val localResponse: FullMealEntityModel? =
                fullMealsDao.getFullMealById(id)
            if (localResponse != null) {
                return@withContext MealsListDomainModel(listOf(localResponse.toFullMealDomainModel()))
            }
            val remoteResponse: MealsListDomainModel = apiService.getMealById(id)
            fullMealsDao.insertAllMeals(remoteResponse.meals.map { it.toFullMealEntityModel() })
            return@withContext remoteResponse
        }
    }
}