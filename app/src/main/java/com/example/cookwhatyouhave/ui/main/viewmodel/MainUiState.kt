package com.example.cookwhatyouhave.ui.main.viewmodel

import com.example.cookwhatyouhave.domain.model.MealCategory
import com.example.cookwhatyouhave.domain.model.MealItem

data class MainUiState(
    val searchQuery: String = "",
    val searchResults: List<MealItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchPerformed: Boolean = false,
    val selectedCategory: MealCategory?=null,
    val activeQuery:String=""
)