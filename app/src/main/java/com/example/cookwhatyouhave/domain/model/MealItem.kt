package com.example.cookwhatyouhave.domain.model

import java.util.UUID

data class MealItem (
    val id:String? = null,
    val name:String? = null,
    val imageUrl:String? = null,
    val instructions:String? = null,
    val ingredients:List<String>
)