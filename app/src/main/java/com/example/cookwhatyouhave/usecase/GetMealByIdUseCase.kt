package com.example.cookwhatyouhave.usecase

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(
    private val repository: MealRepository
){
    suspend operator fun invoke(id:String): MealItem?{
        return repository.getMealById(id)
    }
}