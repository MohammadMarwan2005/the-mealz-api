package com.alaishat.mohammad.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alaishat.mohammad.data.local.daos.AreasDao
import com.alaishat.mohammad.data.local.daos.CategoriesDao
import com.alaishat.mohammad.data.local.daos.FullMealsDao
import com.alaishat.mohammad.data.local.daos.IngredientsDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByAreaDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByCategoriesDao
import com.alaishat.mohammad.data.local.daos.SimpleMealsByIngredientDao
import com.alaishat.mohammad.data.local.model.AreaEntityModel
import com.alaishat.mohammad.data.local.model.CategoryEntityModel
import com.alaishat.mohammad.data.local.model.FullMealEntityModel
import com.alaishat.mohammad.data.local.model.IngredientEntityModel
import com.alaishat.mohammad.data.local.model.SimpleMealByAreaEntityModel
import com.alaishat.mohammad.data.local.model.SimpleMealByCategoryEntityModel
import com.alaishat.mohammad.data.local.model.SimpledMealByIngredientEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */
const val APP_DB_NAME = "main_app_db"

const val DB_VERSION = 12

@Database(
    entities = [IngredientEntityModel::class, CategoryEntityModel::class, AreaEntityModel::class, SimpledMealByIngredientEntityModel::class, SimpleMealByCategoryEntityModel::class, SimpleMealByAreaEntityModel::class, FullMealEntityModel::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {

    abstract fun ingredientsDao(): IngredientsDao

    abstract fun categoriesDao(): CategoriesDao

    abstract fun areasDao(): AreasDao

    abstract fun simpleMealsByIngredientDao(): SimpleMealsByIngredientDao

    abstract fun fullMealsDao(): FullMealsDao

    abstract fun simpleMealsByCategoriesDao(): SimpleMealsByCategoriesDao

    abstract fun simpleMealsByAreaDao(): SimpleMealsByAreaDao




    companion object {

        @Volatile
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDB::class.java,
                        APP_DB_NAME
                    ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                INSTANCE!!
            }
        }
    }
}