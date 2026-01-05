package com.example.cookwhatyouhave.data.mapper

import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.framework.dto.MealDto
import javax.inject.Inject

class MealDtoMapper @Inject constructor() {

    operator fun invoke(dto: MealDto?): MealItem{
        if(dto == null){
            throw NullPointerException("Meal dto was null")
        }
        val ingredientsList = listOfNotNull(
            dto.ingredient1,
            dto.ingredient2,
            dto.ingredient3,
            dto.ingredient4,
            dto.ingredient5,
            dto.ingredient6,
            dto.ingredient7,
            dto.ingredient8,
            dto.ingredient9,
            dto.ingredient10,
            dto.ingredient11,
            dto.ingredient12,
            dto.ingredient13,
            dto.ingredient14,
            dto.ingredient15,
            dto.ingredient16,
            dto.ingredient17,
            dto.ingredient18,
            dto.ingredient19,
            dto.ingredient20
        ).filter { it.isNotBlank() }

        return MealItem(
            id = dto.mealId?:throw Exception ("Could not find meal"),
            name = dto.mealName?:"N/A",
            imageUrl = dto.mealImageUrl?:"N/A",
            instructions = dto.mealInstructions ?:"No instructions available",
            ingredients = ingredientsList
        )
    }
}