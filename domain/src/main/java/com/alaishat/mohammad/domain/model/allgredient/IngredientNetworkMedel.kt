package com.alaishat.mohammad.domain.model.allgredient

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */

data class IngredientDomainModel(
    val idIngredient: String,
    val strDescription: String?,
    val strIngredient: String,
    val strType: String?,
)

fun IngredientDomainModel.getModel(): String = "https://www.themealdb.com/images/ingredients/${this.strIngredient}.png"

