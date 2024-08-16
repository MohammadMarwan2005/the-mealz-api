package com.alaishat.mohammad.mealzapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.domain.model.MealsList.MealsListDomainModel
import com.alaishat.mohammad.domain.usecase.GetMealByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */
@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealByIdUseCase: GetMealByIdUseCase,
) : ViewModel() {
    private val _mealResponse: MutableStateFlow<MealsListDomainModel?> = MutableStateFlow(null)
    val mealsResponse: StateFlow<MealsListDomainModel?> = _mealResponse.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getMealById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _mealResponse.value = getMealByIdUseCase.invoke(id)
            } catch (e: Exception) {
                Log.e("MealViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

}