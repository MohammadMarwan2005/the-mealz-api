package com.alaishat.mohammad.mealzapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alaishat.mohammad.mealzapp.R
import com.alaishat.mohammad.mealzapp.ui.theme.MealzAppTheme

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallSquareCard(
    modifier: Modifier = Modifier,
    title: String = "Shawarma",
    model: Any? = "https://www.themealdb.com/images/media/meals/kcv6hj1598733479.jpg",
    onclick: () -> Unit = { },
    contentScale: ContentScale = ContentScale.Fit
) {
    Card(
        modifier = modifier,
        onClick = onclick,
    ) {

        AsyncImage(
            modifier = Modifier.height(150.dp).fillMaxWidth(),
            model = model,
            contentDescription = "",
//            placeholder = painterResource(id = R.drawable.ic_launcher_background)
            alignment = Alignment.Center,
            contentScale = contentScale
        )
        Text(
            modifier = Modifier
                .requiredWidthIn(max = 150.dp)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
            ,
            text = title, style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSmallSquareCard(modifier: Modifier = Modifier) {
    MealzAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            SmallSquareCard(modifier = modifier)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(text = "Random Meals")
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) {
                        SmallSquareCard()
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Categories")
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) {
                        SmallSquareCard(title = "Beef", model = "https://www.themealdb.com/images/category/beef.png")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Ingredients")
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) {
                        SmallSquareCard(
                            title = "Chicken",
                            model = "https://www.themealdb.com/images/ingredients/Chicken.png"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


    }
}
