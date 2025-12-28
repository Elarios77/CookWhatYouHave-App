package com.example.cookwhatyouhave.usecase

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(id: String): Result<List<MealItem>> {
        if(id.isBlank()){
            return Result.failure(Exception("invalid id"))
        }
        return repository.getMealDetails(id)
    }
}