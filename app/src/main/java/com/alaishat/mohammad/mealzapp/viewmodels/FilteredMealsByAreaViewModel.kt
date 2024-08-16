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
class FilteredMealsByAreaViewModel @Inject constructor(
    private val filteredMealsUseCase: GetFilteredMealsUseCase,
) : ViewModel() {
    private val _filteredMealsByArea: MutableStateFlow<FilteredMealsDomainModel?> = MutableStateFlow(null)
    val filteredMealsByArea: StateFlow<FilteredMealsDomainModel?> = _filteredMealsByArea.asStateFlow()
    val _isReloadingMealsByArea: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingMealsByArea: StateFlow<Boolean> = _isReloadingMealsByArea.asStateFlow()

    fun getFilteredMealsByArea(area: String) {
        viewModelScope.launch {
            _isReloadingMealsByArea.value = true
            Log.d("_isReloading = ", _isReloadingMealsByArea.value.toString())
            try {
                _filteredMealsByArea.value = filteredMealsUseCase.getFilteredMealsByArea(area)
            } catch (e: Exception) {
                Log.d("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isReloadingMealsByArea.value = false
                Log.d("_isReloading = ", _isReloadingMealsByArea.value.toString())
            }
        }
    }



}