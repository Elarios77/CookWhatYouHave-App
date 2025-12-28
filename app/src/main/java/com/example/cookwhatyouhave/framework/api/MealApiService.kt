package com.example.cookwhatyouhave.framework.api


import com.example.cookwhatyouhave.framework.dto.MealResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") id: String
    ): Response<MealResponseDto>

     @GET("filter.php")
     suspend fun getMealsByIngredient(
         @Query("i") ingredient: String
     ): Response<MealResponseDto>
}