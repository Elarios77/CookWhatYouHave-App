package com.example.cookwhatyouhave.usecase

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(meal: MealItem){
        repository.deleteMeal(meal)
    }
}