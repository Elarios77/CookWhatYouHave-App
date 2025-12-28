package com.example.cookwhatyouhave.usecase

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {
    operator fun invoke(): Flow<List<MealItem>> {
        return repository.getFavoriteMeals()
    }
}