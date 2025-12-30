package com.example.cookwhatyouhave.usecase

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(category: String):Result<List<MealItem>>{
        val result = repository.getMealsByCategory(category)
        return result.map { list->
            list.sortedBy { it.name }
        }
    }
}