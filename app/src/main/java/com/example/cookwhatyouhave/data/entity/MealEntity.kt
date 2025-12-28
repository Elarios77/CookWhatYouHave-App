package com.example.cookwhatyouhave.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class MealEntity(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val name: String,
    val imageUrl:String,
    val instructions:String,
    val ingredients: List<String>
)