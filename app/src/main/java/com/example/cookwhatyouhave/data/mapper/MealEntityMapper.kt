package com.example.cookwhatyouhave.data.mapper

import android.R.attr.name
import com.example.cookwhatyouhave.data.entity.MealEntity
import com.example.cookwhatyouhave.domain.model.MealItem
import javax.inject.Inject

class MealEntityMapper @Inject constructor(){

    operator fun invoke(entity: MealEntity?): MealItem?{
        if(entity == null) return null
        return MealItem(
            id = entity.id,
            name = entity.name,
            imageUrl = entity.imageUrl,
            instructions = entity.instructions,
            ingredients = entity.ingredients
        )
    }

    //Reverse mapping
    operator fun invoke(item: MealItem): MealEntity{
        return MealEntity(
            id = item.id ?:"N/A",
            name = item.name?:"N/A",
            imageUrl = item.imageUrl ?: "N/A",
            instructions = item.instructions ?:"N/A",
            ingredients = item.ingredients

        )
    }
}