package com.alaishat.mohammad.data.repo

import android.util.Log
import com.alaishat.mohammad.data.local.daos.IngredientsDao
import com.alaishat.mohammad.data.local.model.IngredientEntityModel
import com.alaishat.mohammad.data.local.model.toIngredientDomainModel
import com.alaishat.mohammad.data.local.model.toIngredientEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientDomainModel
import com.alaishat.mohammad.domain.repo.AllIngredientRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
class AllIngredientRepoImpl(val apiService: APIService, private val ingredientsDao: IngredientsDao) : AllIngredientRepo {
    override suspend fun getIngredientList(): AllIngredientDomainModel {

        return withContext(Dispatchers.IO) {
            val localResponse: List<IngredientEntityModel> =
                ingredientsDao.getAllIngredientsFromDB()
            if (localResponse.isNotEmpty()) {
                return@withContext AllIngredientDomainModel(localResponse.map { it.toIngredientDomainModel() })
            }
            val remoteResponse: AllIngredientDomainModel = apiService.getAllIngredient()
            ingredientsDao.insertAllIngredientToDB(remoteResponse.meals.map { it.toIngredientEntityModel() })
            Log.d("MohammadRoom", "hello")
            return@withContext remoteResponse

        }
    }
}