package com.example.cookwhatyouhave.ui.favorites.viewmodel

import com.example.cookwhatyouhave.domain.model.MealItem

data class FavoritesUiState (
    val favoriteMeals:List<MealItem> = emptyList(),
    val selectedMeal: MealItem?=null
)