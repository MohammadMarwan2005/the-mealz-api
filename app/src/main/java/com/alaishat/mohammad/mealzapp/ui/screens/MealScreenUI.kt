package com.alaishat.mohammad.mealzapp.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alaishat.mohammad.domain.model.MealsListResonse.Meal
import com.alaishat.mohammad.domain.model.MealsListResonse.getIngredientsList
import com.alaishat.mohammad.domain.model.allareas.getAreaModel
import com.alaishat.mohammad.mealzapp.FilteredMeals
import com.alaishat.mohammad.mealzapp.FilteredMealsByArea
import com.alaishat.mohammad.mealzapp.R
import com.alaishat.mohammad.mealzapp.ui.components.MyCircularProgressIndicator
import com.alaishat.mohammad.mealzapp.ui.components.TopAppBar
import com.alaishat.mohammad.mealzapp.viewmodels.MealViewModel
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealScreenUI(
    mealViewModel: MealViewModel = hiltViewModel(),
    mealId: Int,
    navController: NavController,
) {
    LaunchedEffect(Unit) {
        mealViewModel.getMealById(mealId)
    }

    val meals = mealViewModel.mealsResponse.collectAsStateWithLifecycle().value?.meals ?: emptyList()
    var meal: Meal? = null

    if (meals.isNotEmpty())
        meal = meals[0]

    val isLoading: Boolean = mealViewModel.isLoading.collectAsStateWithLifecycle().value

    if (isLoading)
        MyCircularProgressIndicator()
    else
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
                    title = meal?.strMeal ?: ""
                )
            }
        ) {
            Column(
                Modifier.padding(it)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    model = meal?.strMealThumb, contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    item {
                        meal?.strCategory?.let {
                            ElevatedAssistChip(
                                onClick = {
                                    navController.navigate(FilteredMeals.route + "/$it")
                                },
                                label = { Text(modifier = Modifier.padding(8.dp), text = it) },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_category),
                                        contentDescription = ""
                                    )
                                })
                        }
                    }
                    item {
                        meal?.strArea?.let {
                            ElevatedAssistChip(
                                onClick = { navController.navigate(FilteredMealsByArea.route + "/$it") },
                                label = { Text(modifier = Modifier.padding(8.dp), text = it) },
                                leadingIcon = {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_area),
//                                        contentDescription = ""
//                                    )
                                    AsyncImage(
                                        model = getAreaModel(strArea = it),
                                        contentDescription = "",
                                        placeholder = painterResource(id = R.drawable.ic_area)
                                    )
                                }
                            )
                        }
                    }
                    val tags = meal?.strTags?.split(",")
                    tags?.forEach { tag ->
                        item {
//                            Text(modifier = Modifier.padding(8.dp), text = "#$tag", color = MaterialTheme.colorScheme.tertiary, textDecoration = TextDecoration.Underline)
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "# $tag",
                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }
                val pagerState = rememberPagerState(pageCount = { 3 })
                val coroutineScope = rememberCoroutineScope()
                Column {

                    TabRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        selectedTabIndex = pagerState.currentPage
                    ) {
                        Tab(
                            selected = pagerState.currentPage == 0, onClick = {
                                coroutineScope.launch { pagerState.animateScrollToPage(0) }
                            },
                            text = { Text(text = "Ingredients") }
                        )
                        Tab(
                            selected = pagerState.currentPage == 1, onClick = {
                                coroutineScope.launch { pagerState.animateScrollToPage(1) }
                            },
                            text = { Text(text = "Instructions") }
                        )
                        Tab(
                            selected = pagerState.currentPage == 2, onClick = {
                                coroutineScope.launch { pagerState.animateScrollToPage(2) }

                            },
                            text = { Text(text = "More") }
                        )
                    }

                    HorizontalPager(state = pagerState) {
                        LazyColumn(Modifier.fillMaxHeight()) {
                            item {
                                when (it) {
                                    0 -> {
                                        IngredientsLazyList(
                                            meal?.getIngredientsList() ?: emptyList(),
                                            navController = navController
                                        )
                                    }

                                    1 -> {
                                        Text(
                                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                            text = meal?.strInstructions ?: ""
                                        )
                                    }

                                    2 -> {
                                        Column(
                                            Modifier
                                                .padding(horizontal = 16.dp)
                                        ) {
                                            val context = LocalContext.current
                                            meal?.strSource?.let { source ->
                                                if (source.isNotBlank())
                                                    AssistChip(
                                                        modifier = Modifier,
                                                        onClick = {
                                                            openLink(context, source)
                                                        },
                                                        label = { Text(text = "Resource") },
                                                        leadingIcon = {
                                                            Icon(
                                                                painter = painterResource(id = R.drawable.ic_attachment),
                                                                contentDescription = ""
                                                            )
                                                        })
                                            }
                                            meal?.strYoutube?.let { youtubeLink ->
                                                if (youtubeLink.isNotBlank())
                                                    AssistChip(
                                                        onClick = {
                                                            openLink(context, youtubeLink)
                                                        },
                                                        label = { Text(text = "Youtube Link") },
                                                        leadingIcon = {
                                                            Icon(
                                                                painter = painterResource(id = R.drawable.ic_youtube),
                                                                contentDescription = ""
                                                            )
                                                        })
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }
}


fun openLink(context: Context, url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).also { intent ->
        intent.resolveActivity(context.packageManager)?.let {
            context.startActivity(intent)
        }
    }
}
