package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alaishat.mohammad.mealzapp.R
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.ui.components.StaggeredMealsGrid
import com.alaishat.mohammad.mealzapp.viewmodels.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {

    var query by rememberSaveable {
        mutableStateOf("")
    }

    var search by rememberSaveable {
        mutableStateOf(false)
    }

    if (search) {
        LaunchedEffect(Unit) {
            searchViewModel.getSearchResultOn(query)
        }
        search = false
    }

    val mealsResponse = searchViewModel.searchResultResponse.collectAsStateWithLifecycle().value
    val meals = mealsResponse?.meals ?: emptyList()
    val isLoadingSearchResult = searchViewModel.isLoadingSearchResult.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var debounceJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DockedSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            query = query,
                onQueryChange = {
                    query = it

                    debounceJob?.cancel()

                    debounceJob = coroutineScope.launch {
                        delay(400)
                        search = true
                    }

//                    coroutineScope.cancel()
//                    coroutineScope.launch {
//                        val old = it
//                        delay(1000)
//                        if (old == query){
//                            search = true
//    //                        android.widget.Toast.makeText(context, "search", Toast.LENGTH_SHORT).show()
//                        }
//                    }
                },
            onSearch = {
                searchViewModel.getSearchResultOn(it)
            }, active = false, onActiveChange = { },
            trailingIcon = {
                IconButton(onClick = {
                    search = true
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            },
            placeholder = { Text(text = "Search meal by name") },
            leadingIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }
        ) {  }
        if (isLoadingSearchResult)
            MyCircularProgressIndicator()
        else {
            if (meals.isEmpty()) {
                if (mealsResponse == null)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = R.drawable.lets_search), contentDescription = "")
                        Text(
                            text = "Search for a meal by name!",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }
                else Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(painter = painterResource(id = R.drawable.no_meals_found4), contentDescription = "")
                    Text(
                        text = "Sorry, No Meals Found!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            } else
                StaggeredMealsGrid(meals = meals.map {
                    com.alaishat.mohammad.domain.model.filteredmealsbycategory.MealDomainModel(
                        it.idMeal,
                        it.strMeal,
                        it.strMealThumb
                    )
                }, navController = navController)
        }
    }

}