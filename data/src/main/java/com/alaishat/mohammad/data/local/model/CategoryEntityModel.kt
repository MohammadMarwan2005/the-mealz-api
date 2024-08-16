package com.alaishat.mohammad.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alaishat.mohammad.domain.model.allcategories.CategoryDomainModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */

const val CATEGORIES_TABLE_NAME = "categories"

@Entity(tableName = CATEGORIES_TABLE_NAME)
data class CategoryEntityModel(
    @PrimaryKey val idCategory: String,
    val strCategory: String?,
    val strCategoryDescription: String?,
    val strCategoryThumb: String?,
)

fun CategoryEntityModel.toCategoryDomainModel() = CategoryDomainModel(
    idCategory,
    strCategory ?: "",
    strCategoryDescription ?: "",
    strCategoryThumb ?: "",
)

fun CategoryDomainModel.toCategoryEntityModel() = CategoryEntityModel(
    idCategory,
    strCategory,
    strCategoryDescription,
    strCategoryThumb,
)
