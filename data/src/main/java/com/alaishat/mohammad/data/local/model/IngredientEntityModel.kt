package com.alaishat.mohammad.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alaishat.mohammad.domain.model.allgredient.IngredientDomainModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */

const val INGREDIENTS_TABLE_NAME = "ingredients"

@Entity(tableName = INGREDIENTS_TABLE_NAME)
data class IngredientEntityModel(
    @PrimaryKey val ingredientId: Int,
    val strDescription: String,
    val strIngredient: String,
    val strType: String?,
)


fun IngredientEntityModel.toIngredientDomainModel(): IngredientDomainModel {
    return IngredientDomainModel(
        idIngredient = ingredientId.toString(),
        strDescription = strDescription,
        strIngredient = strIngredient,
        strType = this.strType ?: "",
    )
}

fun IngredientDomainModel.toIngredientEntityModel(): IngredientEntityModel =
    IngredientEntityModel(
        ingredientId = idIngredient.toInt(),
        strDescription = strDescription ?: "",
        strIngredient = strIngredient,
        strType = strType ?: "",
    )