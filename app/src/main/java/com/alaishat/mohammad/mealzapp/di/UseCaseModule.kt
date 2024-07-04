package com.alaishat.mohammad.mealzapp.di

import com.alaishat.mohammad.domain.repo.AllAreasRepo
import com.alaishat.mohammad.domain.repo.AllCategoriesDomainRepo
import com.alaishat.mohammad.domain.repo.AllIngredientRepo
import com.alaishat.mohammad.domain.repo.FilteredMealsDomainRepo
import com.alaishat.mohammad.domain.repo.MealByIdRepo
import com.alaishat.mohammad.domain.repo.RandomMealRepo
import com.alaishat.mohammad.domain.repo.SearchResultRepo
import com.alaishat.mohammad.domain.uscase.GetAllAreasUseCase
import com.alaishat.mohammad.domain.uscase.GetAllCategoriesUseCase
import com.alaishat.mohammad.domain.uscase.GetAllIngredientUseCase
import com.alaishat.mohammad.domain.uscase.GetFilteredMealsUseCase
import com.alaishat.mohammad.domain.uscase.GetMealByIdUseCase
import com.alaishat.mohammad.domain.uscase.GetRandomMealUseCase
import com.alaishat.mohammad.domain.uscase.GetSearchResultUseCase
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
object UseCaseModule {

    @Provides
    fun provideGetMealsUseCase(allCategoriesDomainRepo: AllCategoriesDomainRepo): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(allCategoriesDomainRepo)
    }

    @Provides
    fun provideGetFilteredMealsUseCase(filteredMealsDomainRepo: FilteredMealsDomainRepo): GetFilteredMealsUseCase {
        return GetFilteredMealsUseCase(filteredMealsDomainRepo)
    }

    @Provides
    fun provideGetAllIngredientUseCase(allIngredientRepo: AllIngredientRepo): GetAllIngredientUseCase {
        return GetAllIngredientUseCase(allIngredientRepo)
    }

    @Provides
    fun provideGetMealByIdUseCase(mealByIdRepo: MealByIdRepo): GetMealByIdUseCase {
        return GetMealByIdUseCase(mealByIdRepo)
    }

    @Provides
    fun provideGetAllAreasUseCase(allAreasRepo: AllAreasRepo): GetAllAreasUseCase = GetAllAreasUseCase(allAreasRepo)

    @Provides
    fun provideGetRandomMealUseCase(randomMealRepo: RandomMealRepo): GetRandomMealUseCase = GetRandomMealUseCase(randomMealRepo)

    @Provides
    fun provideGetSearchResultUseCase(searchResultRepo: SearchResultRepo): GetSearchResultUseCase = GetSearchResultUseCase(searchResultRepo)

//    @Provides
//    fun provideGetFilteredMealsByArea(filteredMealsByAreaRepo: FilteredMealsByAreaRepo): GetMealsByAreaUseCase {
//        return GetMealsByAreaUseCase(filteredMealsByAreaRepo)
//    }
//    @Provides
//    fun provideGetFilteredMealsByIngredient(filteredMealsByIngredientRepo: FilteredMealsByIngredientRepo): GetFilteredMealsByIngredientUseCase {
//        return GetFilteredMealsByIngredientUseCase(filteredMealsByIngredientRepo)
//    }
}