package com.example.cookwhatyouhave.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cookwhatyouhave.data.entity.MealEntity
import com.example.cookwhatyouhave.framework.dao.MealDao

@Database(
    entities = [MealEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun mealDao(): MealDao
}