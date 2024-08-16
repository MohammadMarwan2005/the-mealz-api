package com.alaishat.mohammad.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alaishat.mohammad.domain.model.allareas.AreaDomainModel

/**
 * Created by Mohammad Al-Aishat on Aug/14/2024.
 * Mealz App Project.
 */

const val AREAS_TABLE_NAME = "areas"

@Entity(tableName = AREAS_TABLE_NAME)
data class AreaEntityModel(
    @PrimaryKey val strArea: String,
)

fun AreaEntityModel.toAreaDomainModel() = AreaDomainModel(strArea ?: "")

fun AreaDomainModel.toAreaEntityModel() = AreaEntityModel(strArea)


