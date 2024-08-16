package com.alaishat.mohammad.mealzapp.di

import android.content.Context
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alaishat.mohammad.data.local.AppDB
import com.alaishat.mohammad.data.local.daos.AreasDao
import com.alaishat.mohammad.data.local.daos.CategoriesDao
import com.alaishat.mohammad.data.local.daos.FullMealsDao
import com.alaishat.mohammad.data.local.daos.IngredientsDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByAreaDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByCategoriesDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByIngredientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): AppDB {
        return AppDB.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideIngredientsDao(db: AppDB): IngredientsDao {
        return db.ingredientsDao()
    }


    @Provides
    @Singleton
    fun provideCategoriesDao(db: AppDB): CategoriesDao {
        return db.categoriesDao()
    }

    @Provides
    @Singleton
    fun provideAreasDao(db: AppDB): AreasDao {
        return db.areasDao()
    }

    @Provides
    @Singleton
    fun provideFullMealDao(db: AppDB): FullMealsDao {
        return db.fullMealsDao()
    }

    @Provides
    @Singleton
    fun provideSimpleMealsByIngredientsDao(db: AppDB): SimpleMealsByIngredientDao {
        return db.simpleMealsByIngredientDao()
    }

    @Provides
    @Singleton
    fun provideSimpleMealsByCategoryDao(db: AppDB): SimpleMealsByCategoriesDao {
        return db.simpleMealsByCategoriesDao()
    }

    @Provides
    @Singleton
    fun provideSimpleMealsByAreaDao(db: AppDB): SimpleMealsByAreaDao {
        return db.simpleMealsByAreaDao()
    }


}
