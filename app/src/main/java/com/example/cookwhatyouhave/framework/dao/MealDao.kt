package com.example.cookwhatyouhave.framework.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookwhatyouhave.data.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals:List<MealEntity>)

    @Query("SELECT * FROM recipes")
    fun getAllMeals() : Flow<List<MealEntity>>

    @Query("SELECT * FROM recipes WHERE id =:mealId")
    suspend fun getMealById(mealId: String): MealEntity?

    @Delete
    suspend fun deleteMeal(meal: MealEntity)
}