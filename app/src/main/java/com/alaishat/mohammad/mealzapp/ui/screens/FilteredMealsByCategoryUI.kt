package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.MealDomainModel
import com.alaishat.mohammad.mealzapp.ui.components.MealItem
import com.alaishat.mohammad.mealzapp.ui.components.MealsUI
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.ui.theme.MealzAppTheme
import com.alaishat.mohammad.mealzapp.viewmodels.FilteredMealsViewByCategoryModel

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */

@Composable
fun FilteredMealsByCategoryUI(
    categoryName: String,
    filteredMealsViewByCategoryModel: FilteredMealsViewByCategoryModel = hiltViewModel(),
    navController: NavController,
) {

    LaunchedEffect(Unit) {
        filteredMealsViewByCategoryModel.getFilteredMealsByCategory(categoryName)
    }
    val filteredMeals by filteredMealsViewByCategoryModel.filteredMealsByCategory.collectAsStateWithLifecycle()
    val isLoading by filteredMealsViewByCategoryModel.isLoadingMealsByCategory.collectAsStateWithLifecycle()
    if (isLoading)
        MyCircularProgressIndicator()
    else
        MealsUI(
            meals = filteredMeals?.meals ?: emptyList(),
            navController = navController,
            titleString = categoryName,
            topAppBarTitle = "$categoryName Category"
        )
}


@Preview(showBackground = true)
@Composable
private fun PreviewMealItem() {
    MealzAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MealItem(MealDomainModel("3", "This si a title", "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"))
        }
    }
}


