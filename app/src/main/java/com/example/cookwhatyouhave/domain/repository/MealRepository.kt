package com.example.cookwhatyouhave.domain.repository

import com.example.cookwhatyouhave.domain.model.MealItem
import kotlinx.coroutines.flow.Flow
import okhttp3.Response

interface MealRepository {

    suspend fun getMealsByIngredient(ingredient: String): Result<List<MealItem>>
    suspend fun getMealDetails(id: String): Result<List<MealItem>>

    fun getFavoriteMeals(): Flow<List<MealItem>>
    suspend fun saveMeal(meal: MealItem)
    suspend fun deleteMeal(meal: MealItem)
    suspend fun getMealById(id: String): MealItem?
}