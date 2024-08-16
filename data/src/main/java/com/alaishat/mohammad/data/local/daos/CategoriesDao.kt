package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alaishat.mohammad.data.local.model.CATEGORIES_TABLE_NAME
import com.alaishat.mohammad.data.local.model.CategoryEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */


@Dao
interface CategoriesDao {

    @Query("SELECT * FROM $CATEGORIES_TABLE_NAME")
    fun getAllCategories(): List<CategoryEntityModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(categories: List<CategoryEntityModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertACategory(category: CategoryEntityModel)

}