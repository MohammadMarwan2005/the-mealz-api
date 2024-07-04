package com.alaishat.mohammad.mealzapp.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alaishat.mohammad.domain.model.MealsListResonse.MealsListResponse
import com.alaishat.mohammad.domain.model.allareas.getModel
import com.alaishat.mohammad.domain.model.allcategories.AllCategoriesResponse
import com.alaishat.mohammad.domain.model.allgredient.getModel
import com.alaishat.mohammad.mealzapp.FilteredMeals
import com.alaishat.mohammad.mealzapp.FilteredMealsByArea
import com.alaishat.mohammad.mealzapp.FilteredMealsByIngredient
import com.alaishat.mohammad.mealzapp.MealScreen
import com.alaishat.mohammad.mealzapp.SearchScreen
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.ui.components.SmallSquareCard
import com.alaishat.mohammad.mealzapp.ui.components.TopAppBar
import com.alaishat.mohammad.mealzapp.viewmodels.HomeViewModel

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val categoriesResponse: AllCategoriesResponse? = homeViewModel.categories.collectAsStateWithLifecycle().value
    val categories = categoriesResponse?.categories ?: emptyList()
    val isLoadingAllCategories = homeViewModel.isLoadingCategories.collectAsStateWithLifecycle().value

    val ingredientsResponse = homeViewModel.ingredients.collectAsStateWithLifecycle().value
    val ingredients = ingredientsResponse?.meals ?: emptyList()
    val isLoadingIngredients = homeViewModel.isLoadingIngredient.collectAsStateWithLifecycle().value

    val areasResponse = homeViewModel.areas.collectAsStateWithLifecycle().value
    val areas = areasResponse?.meals ?: emptyList()
    val isLoadingAreas = homeViewModel.isLoadingAreas.collectAsStateWithLifecycle().value

//    val randomMeals: List<Meal> = homeViewModel.randomMealResponse.collectAsStateWithLifecycle().value?.meals ?: emptyList()
    val randomMeals: List<MealsListResponse?> = homeViewModel.meals.collectAsStateWithLifecycle().value
//    val isLoadingRandomMeal = homeViewModel.isLoadingRandomMeal.collectAsStateWithLifecycle().value
    val isLoadingRandomMealsList = homeViewModel.isLoadingRandomMealsList.collectAsStateWithLifecycle().value

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (categoriesResponse == null)
            homeViewModel.getCategories()
    }
    LaunchedEffect(Unit) {
        if (ingredientsResponse == null)
            homeViewModel.getAllIngredients()
    }
    LaunchedEffect(Unit) {
        if (areasResponse == null)
            homeViewModel.getAllAreas()
    }
    LaunchedEffect(Unit) {
        homeViewModel.getRandomMeal()
    }
    LaunchedEffect(Unit) {
        if (randomMeals.getOrNull(0) == null)
            homeViewModel.getMeals()
    }

    var update by rememberSaveable {
        mutableStateOf(false)
    }

    if (update) {
        LaunchedEffect(Unit) {
            homeViewModel.getMeals()
        }
        update = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                leadingIcon = {
                    IconButton(
                        modifier = Modifier.padding(8.dp),
                        onClick = { Toast.makeText(context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show() }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            navController.navigate(SearchScreen.route)
                        }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding(top = 0.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Random Meals", style = MaterialTheme.typography.titleLarge)
                        IconButton(onClick = {
                            homeViewModel.clearRandomMeals()
                            update = true
                        }) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }


                item {
//                    if (isLoadingRandomMealsList)
                    if (randomMeals.isEmpty())
                        MyCircularProgressIndicator()
                    else
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            randomMeals.forEach { mealsListResponse ->
                                mealsListResponse?.let {
                                    items(mealsListResponse.meals.size) { mealIndex ->
                                        SmallSquareCard(
                                            title = it.meals[mealIndex].strMeal,
                                            model = it.meals[mealIndex].strMealThumb,
                                            onclick = {
                                                navController.navigate(MealScreen.route + "/${it.meals[mealIndex].idMeal}")
                                            }
                                        )
                                    }
                                }
                            }
                        }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Categories", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    if (isLoadingAllCategories)
                        MyCircularProgressIndicator()
                    else
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(categories.size) {
                                SmallSquareCard(
                                    title = categories[it].strCategory,
                                    model = categories[it].strCategoryThumb,
                                    onclick = {
                                        navController.navigate(FilteredMeals.route + "/${categories[it].strCategory}")
                                    }
                                )
                            }
                        }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Ingredients", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    if (isLoadingIngredients)
                        MyCircularProgressIndicator()
                    else
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(ingredients.size) {
//                            Log.d("Home: ", ingredients[it].ingredientModel.toString())
                                SmallSquareCard(
                                    title = ingredients[it].strIngredient,
                                    model = ingredients[it].getModel(),
                                    onclick = {
                                        navController.navigate(FilteredMealsByIngredient.route + "/${ingredients[it].strIngredient}")
                                    }
                                )
                            }
                        }
                }



                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Countries", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Log.d("Home: Areas", areas.toString())
                    if (isLoadingAreas)
                        MyCircularProgressIndicator()
                    else
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(areas.size) {
                                SmallSquareCard(
                                    title = areas[it].strArea,
                                    model = areas[it].getModel(),
                                    onclick = {
                                        navController.navigate(FilteredMealsByArea.route + "/${areas[it].strArea}")
                                    },
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                }
            }
        }

    }
}