package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.allareas.AllAreasResponse
import com.alaishat.mohammad.domain.repo.AllAreasRepo

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class AllAreasRepoImpl(private val apiService: APIService): AllAreasRepo {
    override suspend fun getAllAreas(): AllAreasResponse = apiService.getAllAreas()
}