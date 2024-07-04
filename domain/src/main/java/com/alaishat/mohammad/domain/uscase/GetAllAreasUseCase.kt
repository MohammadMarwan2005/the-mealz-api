package com.alaishat.mohammad.domain.uscase

import com.alaishat.mohammad.domain.repo.AllAreasRepo

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */
class GetAllAreasUseCase(private val allAreasRepo: AllAreasRepo) {
    suspend operator fun invoke() = allAreasRepo.getAllAreas()
}