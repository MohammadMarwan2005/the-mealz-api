package com.alaishat.mohammad.domain.repo

import com.alaishat.mohammad.domain.model.allareas.AllAreasDomainModel

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
interface AllAreasRepo {
    suspend fun getAllAreas(): AllAreasDomainModel
}