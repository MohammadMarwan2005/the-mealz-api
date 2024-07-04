package com.alaishat.mohammad.mealzapp

/**
 * Created by Mohammad Al-Aishat on Jun/28/2024.
 * Mealz App Project.
 */
interface NavigationDestination {
    val route: String
}

object Categories : NavigationDestination {
    override val route = "Categories"
}

object FilteredMeals : NavigationDestination {
    override val route: String = "FilteredMeals"
    const val CATEGORY_NAME_KEY = "CATEGORY_NAME_KEY"
}

object Home: NavigationDestination {
    override val route: String = "Home"
}

object MealScreen: NavigationDestination{
    override val route: String = "MealScreenUI"
    const val MEAL_ID_KEY = "MEAL_ID_KEY"
}

object FilteredMealsByIngredient: NavigationDestination {
    override val route: String = "FilteredMealsByIngredient"
    const val INGREDIENT_NAME_KEY = "INGREDIENT_NAME_KEY"
}
object FilteredMealsByArea: NavigationDestination {
    override val route: String = "FilteredMealsByArea"
    const val AREA_NAME_KEY = "AREA_NAME_KEY"
}

object SearchScreen: NavigationDestination {
    override val route: String = "SearchScreen"
}

