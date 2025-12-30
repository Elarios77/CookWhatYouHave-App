package com.example.cookwhatyouhave.data.datasource

import com.example.cookwhatyouhave.domain.model.MealCategory
import com.example.cookwhatyouhave.framework.dto.MealResponseDto

interface RemoteDataSource {
    suspend fun fetchMealDetails(id: String): MealResponseDto?
    suspend fun fetchMealsByIngredient(ingredient: String): MealResponseDto?
    suspend fun fetchMealsByCategory(category: String): MealResponseDto?
}