package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.local.daos.FullMealsDao
import com.alaishat.mohammad.data.local.model.toFullMealDomainModel
import com.alaishat.mohammad.data.local.model.toFullMealEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.repo.RandomMealRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class RandomMealRepoImpl(private val apiService: APIService, private val fullMealsDao: FullMealsDao) :
    RandomMealRepo {
    override suspend fun getRandomMeals(): MealsListDomainModel {

        return withContext(Dispatchers.IO) {
            try {
                val remoteResponse = apiService.getRandomMeal()
                fullMealsDao.insertAllMeals(remoteResponse.meals.map { it.toFullMealEntityModel() })
                return@withContext remoteResponse
            } catch (e: Exception) {
                val localResponse = fullMealsDao.getAllMeals()
                if (localResponse.isNotEmpty()) {
                    val random = Random.nextInt(localResponse.size)
                    return@withContext MealsListDomainModel(listOf(localResponse.map { it.toFullMealDomainModel() }[random]))
                }
                return@withContext MealsListDomainModel(emptyList())
            }
        }
    }
}