package com.alaishat.mohammad.data.repo

import android.util.Log
import com.alaishat.mohammad.data.local.daos.CategoriesDao
import com.alaishat.mohammad.data.local.model.CategoryEntityModel
import com.alaishat.mohammad.data.local.model.IngredientEntityModel
import com.alaishat.mohammad.data.local.model.toCategoryDomainModel
import com.alaishat.mohammad.data.local.model.toCategoryEntityModel
import com.alaishat.mohammad.data.local.model.toIngredientDomainModel
import com.alaishat.mohammad.data.local.model.toIngredientEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesDomainModel
import com.alaishat.mohammad.domain.model.allcategories.CategoryDomainModel
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientDomainModel
import com.alaishat.mohammad.domain.repo.AllCategoriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */
class CategoriesRepoImpl(private val apiService: APIService, private val categoriesDao: CategoriesDao) :
    AllCategoriesRepo {
    override suspend fun getAllCategoriesFromRemote(): AllCategoriesDomainModel {

        return withContext(Dispatchers.IO) {
            val localResponse: List<CategoryEntityModel> =
                categoriesDao.getAllCategories()
            if (localResponse.isNotEmpty()) {
                return@withContext AllCategoriesDomainModel(localResponse.map { it.toCategoryDomainModel() })
            }
            val remoteResponse: AllCategoriesDomainModel = apiService.getAllCategoriesMeals()
            categoriesDao.insertAllCategories(remoteResponse.categories.map { it.toCategoryEntityModel() })
            Log.d("MohammadRoom", "hello")
            return@withContext remoteResponse
        }
    }
}