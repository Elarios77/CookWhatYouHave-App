package com.example.cookwhatyouhave.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealResponseDto (
    @Json(name = "meals") val meals:List<MealDto>?
)