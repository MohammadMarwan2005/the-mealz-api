package com.alaishat.mohammad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alaishat.mohammad.data.local.model.INGREDIENTS_TABLE_NAME
import com.alaishat.mohammad.data.local.model.IngredientEntityModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM $INGREDIENTS_TABLE_NAME")
    fun getAllIngredientsFromDB(): List<IngredientEntityModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnIngredientToDB(ingredientEntityModel: IngredientEntityModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllIngredientToDB(ingredients: List<IngredientEntityModel>)


}