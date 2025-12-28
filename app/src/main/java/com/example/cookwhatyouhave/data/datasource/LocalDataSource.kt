package com.example.cookwhatyouhave.data.datasource

import com.example.cookwhatyouhave.data.entity.MealEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllMeals(): Flow<List<MealEntity>>
    suspend fun insertMeal(meal: MealEntity)
    suspend fun insertMeals(meals:List<MealEntity>)
    suspend fun deleteMeal(meal: MealEntity)
    suspend fun getMealById(mealId: String): MealEntity?
}