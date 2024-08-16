package com.alaishat.mohammad.mealzapp.di

import android.util.Log
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.model.allareas.AllAreasDomainModel
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientDomainModel
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesDomainModel
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsDomainModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */

const val BASE_URL = "https://www.themealdb.com/api/json/v1/1"
val getAllCategoriesUrlString = "$BASE_URL/categories.php"
val getMealByIdUrlString = "$BASE_URL/lookup.php?i="
val getRandomMealUrlString = "$BASE_URL/random.php"

val getAllIngredientsUrlString = "$BASE_URL/list.php?i=list"
val allAreasUrlString = "$BASE_URL/list.php?a=list"

val filteredMealsByCategoryUrlString = "$BASE_URL/filter.php?c="
val filteredMealsByAreaUrlString = "$BASE_URL/filter.php?a="
val filteredMealsByIngredientUrlString = "$BASE_URL/filter.php?i="
val getSearchResultUrlString = "$BASE_URL/search.php?s="

@Module // hilt, this is a module...
@InstallIn(SingletonComponent::class)   // hilt, this is a singleton
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    val gson = Gson()

    @Provides
    @Singleton
    fun provideApiService(httpClient: HttpClient): APIService {
        return object : APIService {
            override suspend fun getAllCategoriesMeals(): AllCategoriesDomainModel {
                val response: HttpResponse = httpClient.get(urlString = getAllCategoriesUrlString)
                val jsonString = response.bodyAsText()
                val result: AllCategoriesDomainModel = gson.fromJson(jsonString, AllCategoriesDomainModel::class.java)
                return result
            }

            override suspend fun getFilteredMealsByCategory(category: String): FilteredMealsDomainModel {
                val response = httpClient.get(urlString = filteredMealsByCategoryUrlString + category)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, FilteredMealsDomainModel::class.java)
                return result
            }

            override suspend fun getAllIngredient(): AllIngredientDomainModel {
                val response = httpClient.get(urlString = getAllIngredientsUrlString)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, AllIngredientDomainModel::class.java)
                Log.d("getAllIngredient json:", jsonString)
                Log.d("getAllIngredient, result:", result.toString())
                return result
            }

            override suspend fun getAllAreas(): AllAreasDomainModel {
                val response = httpClient.get(urlString = allAreasUrlString)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, AllAreasDomainModel::class.java)
                Log.d("All areas json:", jsonString)
                Log.d("All areas, result:", result.toString())
                return result
            }

            override suspend fun getMealById(id: Int): MealsListDomainModel {
                val response = httpClient.get(urlString = getMealByIdUrlString + id.toString())
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, MealsListDomainModel::class.java)
                Log.d("getMealById $id result: ", result.toString())
                return result
            }

            override suspend fun getMealsByArea(area: String): FilteredMealsDomainModel {
                val response = httpClient.get(urlString = filteredMealsByAreaUrlString+ area)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, FilteredMealsDomainModel::class.java)
                return result
            }
            override suspend fun getMealsByIngredient(ingredient: String): FilteredMealsDomainModel {
                val response = httpClient.get(urlString = filteredMealsByIngredientUrlString+ ingredient)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, FilteredMealsDomainModel::class.java)
                return result
            }

            override suspend fun getRandomMeal(): MealsListDomainModel {
                val response = httpClient.get(urlString = getRandomMealUrlString)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, MealsListDomainModel::class.java)
                Log.d("Random Meal: result: ", result.toString())
                return result
            }

            override suspend fun getSearchResultOn(searchQuery: String): MealsListDomainModel {
                val response = httpClient.get(urlString = getSearchResultUrlString + searchQuery)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, MealsListDomainModel::class.java)
                Log.d("Search $searchQuery result: ", result.toString())
                return result
            }
        }
    }
}