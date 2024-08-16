package com.alaishat.mohammad.domain.model.MealsList

import com.alaishat.mohammad.domain.model.filteredmealsbycategory.MealDomainModel

data class FullMealDomainModel(
    val dateModified: Any,
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: Any,
    val strDrinkAlternate: Any,
    val strImageSource: Any,
    val strIngredient1: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient2: String,
    val strIngredient20: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
    val strMeasure1: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure2: String,
    val strMeasure20: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strSource: String,
    val strTags: String?,
    val strYoutube: String,
)
fun FullMealDomainModel.getIngredientsList(): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()

    if (strIngredient1.isNullOrBlank() || strMeasure1.isNullOrBlank())
        return result
    result.add(strIngredient1 to strMeasure1)

    if (strIngredient2.isNullOrBlank() || strMeasure2.isNullOrBlank())
        return result
    result.add(strIngredient2 to strMeasure2)

    if (strIngredient3.isNullOrBlank() || strMeasure3.isNullOrBlank())
        return result
    result.add(strIngredient3 to strMeasure3)

    if (strIngredient4.isNullOrBlank() || strMeasure4.isNullOrBlank())
        return result
    result.add(strIngredient4 to strMeasure4)

    if (strIngredient5.isNullOrBlank() || strMeasure5.isNullOrBlank())
        return result
    result.add(strIngredient5 to strMeasure5)

    if (strIngredient6.isNullOrBlank() || strMeasure6.isNullOrBlank())
        return result
    result.add(strIngredient6 to strMeasure6)

    if (strIngredient7.isNullOrBlank() || strMeasure7.isNullOrBlank())
        return result
    result.add(strIngredient7 to strMeasure7)

    if (strIngredient8.isNullOrBlank() || strMeasure8.isNullOrBlank())
        return result
    result.add(strIngredient8 to strMeasure8)

    if (strIngredient9.isNullOrBlank() || strMeasure9.isNullOrBlank())
        return result
    result.add(strIngredient9 to strMeasure9)

    if (strIngredient10.isNullOrBlank() || strMeasure10.isNullOrBlank())
        return result
    result.add(strIngredient10 to strMeasure10)

    if (strIngredient11.isNullOrBlank() || strMeasure11.isNullOrBlank())
        return result
    result.add(strIngredient11 to strMeasure11)

    if (strIngredient12.isNullOrBlank() || strMeasure12.isNullOrBlank())
        return result
    result.add(strIngredient12 to strMeasure12)

    if (strIngredient13.isNullOrBlank() || strMeasure13.isNullOrBlank())
        return result
    result.add(strIngredient13 to strMeasure13)

    if (strIngredient14.isNullOrBlank() || strMeasure14.isNullOrBlank())
        return result
    result.add(strIngredient14 to strMeasure14)

    if (strIngredient15.isNullOrBlank() || strMeasure15.isNullOrBlank())
        return result
    result.add(strIngredient15 to strMeasure15)

    if (strIngredient16.isNullOrBlank() || strMeasure16.isNullOrBlank())
        return result
    result.add(strIngredient16 to strMeasure16)

    if (strIngredient17.isNullOrBlank() || strMeasure17.isNullOrBlank())
        return result
    result.add(strIngredient17 to strMeasure17)

    if (strIngredient18.isNullOrBlank() || strMeasure18.isNullOrBlank())
        return result
    result.add(strIngredient18 to strMeasure18)

    if (strIngredient19.isNullOrBlank() || strMeasure19.isNullOrBlank())
        return result
    result.add(strIngredient19 to strMeasure19)

    if (strIngredient20.isNullOrBlank() || strMeasure20.isNullOrBlank())
        return result
    result.add(strIngredient20 to strMeasure20)

    return result
}
