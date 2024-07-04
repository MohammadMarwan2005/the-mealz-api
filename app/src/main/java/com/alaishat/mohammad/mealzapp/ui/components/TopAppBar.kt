package com.alaishat.mohammad.mealzapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.mealzapp.R
import com.alaishat.mohammad.mealzapp.ui.theme.MealzAppTheme

/**
 * Created by Mohammad Al-Aishat on Jun/29/2024.
 * Mealz App Project.
 */

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
//    showTrailingIcon: Boolean = true,
    leadingIcon: @Composable () -> Unit = {
        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
        }
    },
    trailingIcon: @Composable () -> Unit = {
        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        }
    },
    showAppIcon: Boolean = true,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    title: String = "",
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//            .shadow(elevation = 5.dp, RectangleShape)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement
        ) {
            leadingIcon()
            if (showAppIcon)
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.top_bar_icon), contentDescription = "",
                    alignment = Alignment.Center
                )
            else
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            trailingIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBar(modifier: Modifier = Modifier) {
    MealzAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TopAppBar(modifier = modifier)
        }
    }
}
