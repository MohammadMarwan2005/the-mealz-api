package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alaishat.mohammad.mealzapp.ui.components.MealsUI
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.viewmodels.FilteredMealsByIngredientViewModel

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */

@Composable
fun FilteredMealsByIngredientUI(
    ingredientName: String,
    filteredMealsByIngredientViewModel: FilteredMealsByIngredientViewModel = hiltViewModel(),
    navController: NavController
) {

    LaunchedEffect(Unit) {
        filteredMealsByIngredientViewModel.getFilteredMealsByIngredient(ingredientName)
    }


    val filteredMeals = filteredMealsByIngredientViewModel.filteredMealsByIngredient.collectAsStateWithLifecycle().value
    val isLoading by filteredMealsByIngredientViewModel.isLoadingMealsByIngredient.collectAsStateWithLifecycle()
    if (isLoading)
        MyCircularProgressIndicator()
    else
        MealsUI(
            meals = filteredMeals?.meals ?: emptyList(),
            navController = navController,
            titleString = ingredientName,
            topAppBarTitle = "Meals Contains $ingredientName"
        )

}