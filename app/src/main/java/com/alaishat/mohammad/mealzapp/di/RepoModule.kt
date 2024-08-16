package com.alaishat.mohammad.mealzapp.di

import com.alaishat.mohammad.data.local.daos.AreasDao
import com.alaishat.mohammad.data.local.daos.CategoriesDao
import com.alaishat.mohammad.data.local.daos.FullMealsDao
import com.alaishat.mohammad.data.local.daos.IngredientsDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByAreaDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByCategoriesDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByIngredientDao
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.data.repo.AllAreasRepoImpl
import com.alaishat.mohammad.data.repo.AllIngredientRepoImpl
import com.alaishat.mohammad.data.repo.CategoriesRepoImpl
import com.alaishat.mohammad.data.repo.FilteredMealsRepoImpl
import com.alaishat.mohammad.data.repo.MealByIdRepoImpl
import com.alaishat.mohammad.data.repo.RandomMealRepoImpl
import com.alaishat.mohammad.data.repo.SearchResultRepoImpl
import com.alaishat.mohammad.domain.repo.AllAreasRepo
import com.alaishat.mohammad.domain.repo.AllCategoriesRepo
import com.alaishat.mohammad.domain.repo.AllIngredientRepo
import com.alaishat.mohammad.domain.repo.FilteredMealsRepo
import com.alaishat.mohammad.domain.repo.MealByIdRepo
import com.alaishat.mohammad.domain.repo.RandomMealRepo
import com.alaishat.mohammad.domain.repo.SearchResultRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideCategoriesRepo(apiService: APIService, categoriesDao: CategoriesDao): AllCategoriesRepo {
        return CategoriesRepoImpl(apiService, categoriesDao)
    }

    @Provides
    fun provideFilteredMealsDomainRepo(
        apiService: APIService,
        simpleMealsByIngredientDao: SimpleMealsByIngredientDao,
        simpleMealsByCategoriesDao: SimpleMealsByCategoriesDao,
        simpleMealsByAreaDao: SimpleMealsByAreaDao,
    ): FilteredMealsRepo {
        return FilteredMealsRepoImpl(
            apiService,
            simpleMealsByIngredientDao,
            simpleMealsByCategoriesDao,
            simpleMealsByAreaDao
        )
    }

    @Provides
    fun provideAllIngredientsRepo(apiService: APIService, ingredientsDao: IngredientsDao): AllIngredientRepo {
        return AllIngredientRepoImpl(apiService, ingredientsDao)
    }

    @Provides
    fun provideMealByIdRepo(apiService: APIService, fullMealsDao: FullMealsDao): MealByIdRepo {
        return MealByIdRepoImpl(apiService, fullMealsDao)
    }

    @Provides
    fun provideAllAreasRepo(apiService: APIService, areasDao: AreasDao): AllAreasRepo =
        AllAreasRepoImpl(apiService, areasDao)

    @Provides
    fun provideRandomMealRepo(apiService: APIService, fullMealsDao: FullMealsDao): RandomMealRepo =
        RandomMealRepoImpl(apiService, fullMealsDao)

    @Provides
    fun provideSearchResultRepo(
        apiService: APIService,
        fullMealsDao: FullMealsDao,
        simpleMealsByIngredientDao: SimpleMealsByIngredientDao,
        simpleMealsByCategoriesDao: SimpleMealsByCategoriesDao,
        simpleMealsByAreaDao: SimpleMealsByAreaDao,
    ): SearchResultRepo = SearchResultRepoImpl(
        apiService,
        fullMealsDao,
        simpleMealsByIngredientDao,
        simpleMealsByCategoriesDao,
        simpleMealsByAreaDao
    )
}