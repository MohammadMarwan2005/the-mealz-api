package com.alaishat.mohammad.mealzapp.di

import android.util.Log
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.model.allareas.AllAreasResponse
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientResponse
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsResponse
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
            override suspend fun getAllCategoriesMeals(): AllCategoriesResponse {
                val response: HttpResponse = httpClient.get(urlString = getAllCategoriesUrlString)
                val jsonString = response.bodyAsText()
                val result: AllCategoriesResponse = gson.fromJson(jsonString, AllCategoriesResponse::class.java)
                return result
            }

            override suspend fun getFilteredMealsByCategory(category: String): FilteredMealsResponse {
                val response = httpClient.get(urlString = filteredMealsByCategoryUrlString + category)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, FilteredMealsResponse::class.java)
                return result
            }

            override suspend fun getAllIngredient(): AllIngredientResponse {
                val response = httpClient.get(urlString = getAllIngredientsUrlString)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, AllIngredientResponse::class.java)
                Log.d("getAllIngredient json:", jsonString)
                Log.d("getAllIngredient, result:", result.toString())
                return result
            }

            override suspend fun getAllAreas(): AllAreasResponse {
                val response = httpClient.get(urlString = allAreasUrlString)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, AllAreasResponse::class.java)
                Log.d("All areas json:", jsonString)
                Log.d("All areas, result:", result.toString())
                return result
            }

            override suspend fun getMealById(id: Int): MealsListResponse {
                val response = httpClient.get(urlString = getMealByIdUrlString + id.toString())
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, MealsListResponse::class.java)
                Log.d("getMealById $id result: ", result.toString())
                return result
            }

            override suspend fun getMealsByArea(area: String): FilteredMealsResponse {
                val response = httpClient.get(urlString = filteredMealsByAreaUrlString+ area)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, FilteredMealsResponse::class.java)
                return result
            }
            override suspend fun getMealsByIngredient(ingredient: String): FilteredMealsResponse {
                val response = httpClient.get(urlString = filteredMealsByIngredientUrlString+ ingredient)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, FilteredMealsResponse::class.java)
                return result
            }

            override suspend fun getRandomMeal(): MealsListResponse {
                val response = httpClient.get(urlString = getRandomMealUrlString)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, MealsListResponse::class.java)
                Log.d("Random Meal: result: ", result.toString())
                return result
            }

            override suspend fun getSearchResultOn(searchQuery: String): MealsListResponse {
                val response = httpClient.get(urlString = getSearchResultUrlString + searchQuery)
                val jsonString = response.bodyAsText()
                val result = gson.fromJson(jsonString, MealsListResponse::class.java)
                Log.d("Search $searchQuery result: ", result.toString())
                return result
            }


        }
    }
}