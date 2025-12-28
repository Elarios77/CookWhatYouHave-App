package com.example.cookwhatyouhave.usecase

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import javax.inject.Inject

class GetMealsByIngredientUseCase @Inject constructor(
    private val repository : MealRepository
) {
    suspend operator fun invoke(ingredient:String): Result<List<MealItem>>{
        val result = repository.getMealsByIngredient(ingredient)
        return result.map { list->
            list.sortedBy { it.name }
        }
    }
}