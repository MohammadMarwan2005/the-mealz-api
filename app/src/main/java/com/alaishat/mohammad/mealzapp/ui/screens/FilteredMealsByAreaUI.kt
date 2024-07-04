package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alaishat.mohammad.mealzapp.ui.components.MealsUI
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.viewmodels.FilteredMealsByAreaViewModel

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */

@Composable
fun FilteredMealsByAreaUI(
    areaName: String,
    filteredMealsByAreaViewModel: FilteredMealsByAreaViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        filteredMealsByAreaViewModel.getFilteredMealsByArea(areaName)
    }


    val filteredMeals = filteredMealsByAreaViewModel.filteredMealsByArea.collectAsStateWithLifecycle().value
    val isLoading by filteredMealsByAreaViewModel.isLoadingMealsByArea.collectAsStateWithLifecycle()
    if (isLoading)
        MyCircularProgressIndicator()
    else
        MealsUI(
            meals = filteredMeals?.meals ?: emptyList(),
            navController = navController,
            titleString = areaName,
            topAppBarTitle = "$areaName Meals"
        )
}