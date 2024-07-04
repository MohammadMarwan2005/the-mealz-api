package com.alaishat.mohammad.mealzapp.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.FilteredMealsResponse
import com.alaishat.mohammad.domain.uscase.GetFilteredMealsUseCase
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
class FilteredMealsByIngredientViewModel @Inject constructor(
    private val filteredMealsUseCase: GetFilteredMealsUseCase,
) : ViewModel() {


    private val _filteredMealsByIngredient: MutableStateFlow<FilteredMealsResponse?> = MutableStateFlow(null)
    val filteredMealsByIngredient: StateFlow<FilteredMealsResponse?> = _filteredMealsByIngredient.asStateFlow()
    val _isReloadingMealsByIngredient: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingMealsByIngredient: StateFlow<Boolean> = _isReloadingMealsByIngredient.asStateFlow()


    fun getFilteredMealsByIngredient(ingredient: String) {
        viewModelScope.launch {
            _isReloadingMealsByIngredient.value = true
            Log.d("_isReloading = ", _isReloadingMealsByIngredient.value.toString())
            try {
                _filteredMealsByIngredient.value = filteredMealsUseCase.getFilteredMealsByIngredient(ingredient)
            } catch (e: Exception) {
                Log.d("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isReloadingMealsByIngredient.value = false
                Log.d("_isReloading = ", _isReloadingMealsByIngredient.value.toString())
            }
        }
    }
}



