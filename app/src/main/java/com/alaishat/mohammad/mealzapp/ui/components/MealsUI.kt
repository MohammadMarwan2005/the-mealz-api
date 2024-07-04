package com.alaishat.mohammad.mealzapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alaishat.mohammad.domain.model.filteredmealsbycategory.Meal
import com.alaishat.mohammad.mealzapp.MealScreen

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */

@Composable
fun MealsUI(
    meals: List<Meal>,
    navController: NavController,
    titleString: String,
    topAppBarTitle: String = titleString,
) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                showAppIcon = false, leadingIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }, trailingIcon = {},
                horizontalArrangement = Arrangement.Start,
                title = topAppBarTitle
            )
        }
    ) {
        StaggeredMealsGrid(
            paddingValues = it,
            meals = meals,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealItem(meal: Meal) {
    Card(onClick = {}) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(0.4f)
                    .clip(CircleShape),
                model = meal.strMealThumb, contentDescription = ""
            )
            Text(modifier = Modifier.weight(0.6f), text = meal.strMeal, style = MaterialTheme.typography.titleLarge)
        }
    }
}


@Composable
fun StaggeredMealsGrid(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    meals: List<Meal>,
    navController: NavController,
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
    ) {
        items(meals.size) { mealIndex ->
            SmallSquareCard(
                modifier = Modifier.padding(8.dp),
                title = meals[mealIndex].strMeal,
                model = meals[mealIndex].strMealThumb,
                contentScale = ContentScale.Crop,
                onclick = {
                    navController.navigate(MealScreen.route + "/${meals[mealIndex].idMeal}")
                }
            )
        }
    }
}