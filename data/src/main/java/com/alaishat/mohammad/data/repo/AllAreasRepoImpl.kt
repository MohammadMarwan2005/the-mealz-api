package com.alaishat.mohammad.data.repo

import com.alaishat.mohammad.data.local.daos.AreasDao
import com.alaishat.mohammad.data.local.model.AreaEntityModel
import com.alaishat.mohammad.data.local.model.toAreaDomainModel
import com.alaishat.mohammad.data.local.model.toAreaEntityModel
import com.alaishat.mohammad.data.remote.APIService
import com.alaishat.mohammad.domain.model.allareas.AllAreasDomainModel
import com.alaishat.mohammad.domain.repo.AllAreasRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class AllAreasRepoImpl(private val apiService: APIService, private val areasDao: AreasDao): AllAreasRepo {
    override suspend fun getAllAreas(): AllAreasDomainModel {
        return withContext(Dispatchers.IO) {
            val localResponse: List<AreaEntityModel> =
                areasDao.getAllAreas()
            if (localResponse.isNotEmpty()) {
                return@withContext AllAreasDomainModel(localResponse.map { it.toAreaDomainModel() })
            }
            val remoteResponse: AllAreasDomainModel = apiService.getAllAreas()
            areasDao.insertAllAreas(remoteResponse.meals.map { it.toAreaEntityModel() })
            return@withContext remoteResponse
        }
    }
}