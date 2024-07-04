package com.alaishat.mohammad.mealzapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.model.allareas.AllAreasResponse
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse
import com.alaishat.mohammad.domain.model.allgredient.AllIngredientResponse
import com.alaishat.mohammad.domain.uscase.GetAllAreasUseCase
import com.alaishat.mohammad.domain.uscase.GetAllCategoriesUseCase
import com.alaishat.mohammad.domain.uscase.GetAllIngredientUseCase
import com.alaishat.mohammad.domain.uscase.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getAllIngredientUseCase: GetAllIngredientUseCase,
    private val getAllAreasUseCase: GetAllAreasUseCase,
    private val getRandomMealUseCase: GetRandomMealUseCase,
) : ViewModel() {

    private val _categories: MutableStateFlow<AllCategoriesResponse?> = MutableStateFlow(null)
    val categories: StateFlow<AllCategoriesResponse?> = _categories.asStateFlow()
    private val _isLoadingCategories: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingCategories: StateFlow<Boolean> = _isLoadingCategories.asStateFlow()

    private val _ingredients: MutableStateFlow<AllIngredientResponse?> = MutableStateFlow(null)
    val ingredients: StateFlow<AllIngredientResponse?> = _ingredients.asStateFlow()
    private val _isLoadingIngredient: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingIngredient: StateFlow<Boolean> = _isLoadingIngredient.asStateFlow()

    private val _areas: MutableStateFlow<AllAreasResponse?> = MutableStateFlow(null)
    val areas: StateFlow<AllAreasResponse?> = _areas.asStateFlow()
    private val _isLoadingAreas: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingAreas: StateFlow<Boolean> = _isLoadingAreas.asStateFlow()

    private val _randomMeal: MutableStateFlow<MealsListResponse?> = MutableStateFlow(null)
    val randomMealResponse: StateFlow<MealsListResponse?> = _randomMeal.asStateFlow()
    private val _isLoadingRandomMeal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingRandomMeal: StateFlow<Boolean> = _isLoadingRandomMeal.asStateFlow()

    private val _meals: MutableStateFlow<List<MealsListResponse?>> = MutableStateFlow(listOf(null))
    val meals: StateFlow<List<MealsListResponse?>> = _meals.asStateFlow()
    private val _isLoadingRandomMealsList: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingRandomMealsList: StateFlow<Boolean> = _isLoadingRandomMealsList.asStateFlow()

    fun getCategories() {
        viewModelScope.launch {
            _isLoadingCategories.value = true
            try {
                _categories.value = getAllCategoriesUseCase.invoke()
                Log.d("MealsViewModel", _categories.value?.categories.toString())
            } catch (e: Exception) {
                Log.d("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isLoadingCategories.value = false
            }
        }
    }

    fun getAllIngredients() {
        viewModelScope.launch {
            _isLoadingIngredient.value = true
            try {
                _ingredients.value = getAllIngredientUseCase.invoke()
                Log.d("MealsViewModelL Ings", _ingredients.value?.meals.toString())

            } catch (e: Exception) {
                Log.e("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isLoadingIngredient.value = false
            }
        }
    }

    fun getAllAreas() {
        viewModelScope.launch {
            _isLoadingAreas.value = true
            try {
                _areas.value = getAllAreasUseCase.invoke()
                Log.d("MealsViewModelL Areas:", _areas.value?.meals.toString())

            } catch (e: Exception) {
                Log.e("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isLoadingAreas.value = false
            }
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            _isLoadingRandomMeal.value = true
            try {
                _randomMeal.value = getRandomMealUseCase.invoke()
                Log.d("MealsViewModelL random Meal:", _randomMeal.value?.meals.toString())

            } catch (e: Exception) {
                Log.e("MealsViewModel", "Something went wrong: ${e.message}")
            } finally {
                _isLoadingRandomMeal.value = false
            }
        }
    }

    fun getMeals(n: Int = 3) {
        _meals.value = emptyList()
        viewModelScope.launch {
            _isLoadingRandomMealsList.value = true
                try {for (i in 1..n) {
                    _meals.value += (getRandomMealUseCase.invoke())
                }
                    Log.d("_meals.value", _meals.value.toString())
                } catch (e: Exception) {
                    Log.d("_meals.value", "Something went wrong ${e.message}")
                } finally {
                    _isLoadingRandomMealsList.value = false
                }
        }
    }

    fun clearRandomMeals() {
        _meals.value = emptyList()
    }

}