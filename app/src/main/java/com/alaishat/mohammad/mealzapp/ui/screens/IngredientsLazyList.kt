package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alaishat.mohammad.domain.model.allgredient.Ingredient
import com.alaishat.mohammad.domain.model.allgredient.getModel
import com.alaishat.mohammad.mealzapp.FilteredMealsByIngredient
import com.alaishat.mohammad.mealzapp.R

/**
 * Created by Mohammad Al-Aishat on Jun/30/2024.
 * Mealz App Project.
 */

@Composable
fun IngredientsLazyList(ingredients: List<Pair<String, String>>, navController: NavController) {
    Column {
        ingredients.forEach {
            ListItem(
                modifier = Modifier.clickable {
                    navController.navigate(FilteredMealsByIngredient.route + "/${it.first}")
                },
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .requiredSizeIn(minWidth = 50.dp, minHeight = 50.dp, maxWidth = 50.dp, maxHeight = 50.dp)
                            .aspectRatio(1f)
                            .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary), CircleShape)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier
//                            .requiredSizeIn(minWidth = 40.dp, minHeight = 40.dp, maxWidth = 40.dp, maxHeight = 60.dp)
                                .size(40.dp)
//                            .border(border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), CircleShape)
                                .clip(CircleShape),
                            model = Ingredient("", "", it.first, "").getModel(),
                            contentDescription = "",
                            placeholder = painterResource(id = R.drawable.ic_ingredients)
                        )

                    }
                },
                headlineContent = {
                    Text(text = it.first)
                },
                trailingContent = {
                    Text(text = it.second)
                },
            )
        }
    }
}