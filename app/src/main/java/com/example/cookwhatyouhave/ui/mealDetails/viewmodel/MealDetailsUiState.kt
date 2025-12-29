package com.example.cookwhatyouhave.ui.mealDetails.viewmodel

import com.example.cookwhatyouhave.domain.model.MealItem

data class MealDetailsUiState(
    val isLoading:Boolean = false,
    val meal: MealItem?=null,
    val error:String? = null,
    val isFavorite:Boolean = false
)