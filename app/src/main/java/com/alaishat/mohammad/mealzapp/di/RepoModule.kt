package com.alaishat.mohammad.mealzapp.di

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.data.repo.AllAreasRepoImpl
import com.alaishat.mohammad.data.repo.AllIngredientRepoImpl
import com.alaishat.mohammad.data.repo.FilteredMealsDomainRepoImpl
import com.alaishat.mohammad.data.repo.MealByIdRepoImpl
import com.alaishat.mohammad.data.repo.MealDomainRepoImpl
import com.alaishat.mohammad.data.repo.RandomMealRepoImpl
import com.alaishat.mohammad.data.repo.SearchResultRepoImpl
import com.alaishat.mohammad.domain.repo.AllAreasRepo
import com.alaishat.mohammad.domain.repo.AllCategoriesDomainRepo
import com.alaishat.mohammad.domain.repo.AllIngredientRepo
import com.alaishat.mohammad.domain.repo.FilteredMealsDomainRepo
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
    fun provideMealDomainRepo(apiService: APIService): AllCategoriesDomainRepo {
        return MealDomainRepoImpl(apiService)
    }

    @Provides
    fun provideFilteredMealsDomainRepo(apiService: APIService): FilteredMealsDomainRepo {
        return FilteredMealsDomainRepoImpl(apiService)
    }

    @Provides
    fun provideAllIngredientsRepo(apiService: APIService): AllIngredientRepo {
        return AllIngredientRepoImpl(apiService)
    }

    @Provides
    fun provideMealByIdRepo(apiService: APIService): MealByIdRepo {
        return MealByIdRepoImpl(apiService)
    }

    @Provides
    fun provideAllAreasRepo(apiService: APIService): AllAreasRepo = AllAreasRepoImpl(apiService)

    @Provides
    fun provideRandomMealRepo(apiService: APIService): RandomMealRepo = RandomMealRepoImpl(apiService)

    @Provides
    fun provideSearchResultRepo(apiService: APIService): SearchResultRepo = SearchResultRepoImpl(apiService)
}