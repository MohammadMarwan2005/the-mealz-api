package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alaishat.mohammad.domain.model.allcategories.Category
import com.alaishat.mohammad.mealzapp.FilteredMeals
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.ui.theme.MealzAppTheme
import com.alaishat.mohammad.mealzapp.viewmodels.HomeViewModel

/**
 * Created by Mohammad Al-Aishat on Jun/27/2024.
 * Mealz App Project.
 */

@Composable
fun CategoriesUI(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    LaunchedEffect(Unit) {
        homeViewModel.getCategories()
    }
    val categories = homeViewModel.categories.collectAsStateWithLifecycle().value?.categories ?: emptyList()
    val isLoading = homeViewModel.isLoadingCategories.collectAsStateWithLifecycle().value
    if (isLoading)
        MyCircularProgressIndicator()
    else
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories.size) {
                CategoryItem(category = categories[it], onClick = {
                    navController.navigate(FilteredMeals.route + "/${categories[it].strCategory}")
                })
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(category: Category, onClick: () -> Unit = { }) {
    Card(onClick = onClick) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier.clip(CircleShape),
                model = category.strCategoryThumb, contentDescription = ""
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = category.strCategory, style = MaterialTheme.typography.titleLarge)
                Text(text = category.strCategoryDescription)
            }
        }
//        Divider(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMealItem(modifier: Modifier = Modifier) {
    MealzAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CategoryItem(
                Category(
                    "3",
                    "Title",
                    "Description Description Description Description Description Description Description ",
                    "www.www.www"
                )
            )
        }
    }
}
