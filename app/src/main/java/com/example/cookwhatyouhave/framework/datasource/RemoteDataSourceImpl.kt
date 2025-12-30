package com.example.cookwhatyouhave.framework.datasource

import com.example.cookwhatyouhave.data.datasource.RemoteDataSource
import com.example.cookwhatyouhave.framework.api.MealApiService
import com.example.cookwhatyouhave.framework.dto.MealResponseDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val mealApi: MealApiService
) : RemoteDataSource {
    override suspend fun fetchMealDetails(id: String): MealResponseDto? {
        return try {
            val response = mealApi.getMealDetails(id = id)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun fetchMealsByIngredient(ingredient: String): MealResponseDto? {
        return try {
            val response = mealApi.getMealsByIngredient(ingredient)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun fetchMealsByCategory(category: String): MealResponseDto? {
        return try{
            val response = mealApi.getMealsByCategory(category)
            if(response.isSuccessful){
                response.body()
            }else{
                null
            }
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}