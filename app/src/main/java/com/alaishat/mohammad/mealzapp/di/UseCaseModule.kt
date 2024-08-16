package com.alaishat.mohammad.mealzapp.di

import com.alaishat.mohammad.domain.repo.AllAreasRepo
import com.alaishat.mohammad.domain.repo.AllCategoriesRepo
import com.alaishat.mohammad.domain.repo.AllIngredientRepo
import com.alaishat.mohammad.domain.repo.FilteredMealsRepo
import com.alaishat.mohammad.domain.repo.MealByIdRepo
import com.alaishat.mohammad.domain.repo.RandomMealRepo
import com.alaishat.mohammad.domain.repo.SearchResultRepo
import com.alaishat.mohammad.domain.usecase.GetAllAreasUseCase
import com.alaishat.mohammad.domain.usecase.GetAllCategoriesUseCase
import com.alaishat.mohammad.domain.usecase.GetAllIngredientUseCase
import com.alaishat.mohammad.domain.usecase.GetFilteredMealsUseCase
import com.alaishat.mohammad.domain.usecase.GetMealByIdUseCase
import com.alaishat.mohammad.domain.usecase.GetRandomMealUseCase
import com.alaishat.mohammad.domain.usecase.GetSearchResultUseCase
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
    fun provideGetMealsUseCase(allCategoriesRepo: AllCategoriesRepo): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(allCategoriesRepo)
    }

    @Provides
    fun provideGetFilteredMealsUseCase(filteredMealsRepo: FilteredMealsRepo): GetFilteredMealsUseCase {
        return GetFilteredMealsUseCase(filteredMealsRepo)
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