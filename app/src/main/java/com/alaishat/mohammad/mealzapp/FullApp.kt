package com.alaishat.mohammad.mealzapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse
import com.alaishat.mohammad.mealzapp.ui.screens.CategoriesUI
import com.alaishat.mohammad.mealzapp.ui.screens.FilteredMealsByAreaUI
import com.alaishat.mohammad.mealzapp.ui.screens.HomeScreen
import com.alaishat.mohammad.mealzapp.ui.screens.MealScreenUI
import com.alaishat.mohammad.mealzapp.ui.screens.FilteredMealsByCategoryUI
import com.alaishat.mohammad.mealzapp.ui.screens.FilteredMealsByIngredientUI
import com.alaishat.mohammad.mealzapp.ui.screens.SearchScreen
import com.alaishat.mohammad.mealzapp.ui.theme.MealzAppTheme
import com.alaishat.mohammad.mealzapp.viewmodels.FilteredMealsViewByCategoryModel
import com.alaishat.mohammad.mealzapp.viewmodels.HomeViewModel
import com.alaishat.mohammad.mealzapp.viewmodels.MealViewModel

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */

@Composable
fun FullApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
//    LaunchedEffect(Unit) {
//        homeViewModel.getCategories()
//    }
    NavHost(navController = navController, startDestination = Home.route) {
        composable(Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Categories.route) {
            CategoriesUI(navController = navController)
        }
        composable(
            route = FilteredMeals.route + "/{${FilteredMeals.CATEGORY_NAME_KEY}}",
            arguments = listOf(
                navArgument(name = FilteredMeals.CATEGORY_NAME_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val categoryName = it.arguments?.getString(FilteredMeals.CATEGORY_NAME_KEY)
            FilteredMealsByCategoryUI(
                categoryName = categoryName!!,
                navController = navController
            )
        }
        composable(
            route = MealScreen.route + "/{${MealScreen.MEAL_ID_KEY}}",
            arguments = listOf(
                navArgument(name = MealScreen.MEAL_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            val mealId = it.arguments?.getInt(MealScreen.MEAL_ID_KEY) ?: -1
            MealScreenUI(mealId = mealId, navController = navController)
        }
        composable(
            route= FilteredMealsByIngredient.route + "/{${FilteredMealsByIngredient.INGREDIENT_NAME_KEY}}",
            arguments = listOf(
                navArgument(name = FilteredMealsByIngredient.INGREDIENT_NAME_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val ingredientName = it.arguments?.getString(FilteredMealsByIngredient.INGREDIENT_NAME_KEY)
            FilteredMealsByIngredientUI(
                ingredientName = ingredientName!!,
                navController = navController
            )
        }

        composable(
            route = FilteredMealsByArea.route + "/{${FilteredMealsByArea.AREA_NAME_KEY}}",
            arguments = listOf(navArgument(name = FilteredMealsByArea.AREA_NAME_KEY) {
                    type = NavType.StringType
                })
        ) {
            val areaName = it.arguments?.getString(FilteredMealsByArea.AREA_NAME_KEY)
            FilteredMealsByAreaUI(areaName = areaName!!, navController = navController)
        }
        composable(
            route = SearchScreen.route
        ) {
            SearchScreen(
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFullApp(modifier: Modifier = Modifier) {
    MealzAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
        }
    }
}

