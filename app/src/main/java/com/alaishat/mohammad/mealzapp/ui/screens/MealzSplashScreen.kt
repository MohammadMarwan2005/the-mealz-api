package com.alaishat.mohammad.mealzapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

/**
 * Created by Mohammad Al-Aishat on Jul/01/2024.
 * Mealz App Project.
 */

@Composable
fun MealzSplashScreen(navController: NavController) {
    var scale by rememberSaveable {
        mutableStateOf(0f)
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

    }

}