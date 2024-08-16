package com.alaishat.mohammad.mealzapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsDomainModel
import com.alaishat.mohammad.domain.usecase.GetFilteredMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
@HiltViewModel
class FilteredMealsViewByCategoryModel @Inject constructor(
    private val filteredMealsUseCase: GetFilteredMealsUseCase,
) : ViewModel() {
    private val _filteredMealsByCategory: MutableStateFlow<FilteredMealsDomainModel?> = MutableStateFlow(null)
    val filteredMealsByCategory: StateFlow<FilteredMealsDomainModel?> = _filteredMealsByCategory.asStateFlow()
    val _isReloadingMealsByCategory: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingMealsByCategory: StateFlow<Boolean> = _isReloadingMealsByCategory.asStateFlow()


    fun getFilteredMealsByCategory(category: String) {
        viewModelScope.launch {
            _isReloadingMealsByCategory.value = true
            Log.d("_isReloading = ", _isReloadingMealsByCategory.value.toString())
            try {
                _filteredMealsByCategory.value = filteredMealsUseCase.getFilteredMealsByCategory(category)
            } catch (e: Exception) {
                Log.d("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isReloadingMealsByCategory.value = false
                Log.d("_isReloading = ", _isReloadingMealsByCategory.value.toString())
            }
        }
    }
}