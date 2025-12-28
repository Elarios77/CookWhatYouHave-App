package com.example.cookwhatyouhave.framework.datasource

import com.example.cookwhatyouhave.data.datasource.LocalDataSource
import com.example.cookwhatyouhave.data.entity.MealEntity
import com.example.cookwhatyouhave.framework.dao.MealDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl  @Inject constructor(
    private val dao : MealDao
): LocalDataSource{
    override fun getAllMeals(): Flow<List<MealEntity>> {
        return dao.getAllMeals()
    }

    override suspend fun insertMeal(meal: MealEntity) {
        dao.insertMeal(meal)
    }

    override suspend fun insertMeals(meals: List<MealEntity>) {
        dao.insertMeals(meals)
    }

    override suspend fun deleteMeal(meal: MealEntity) {
        dao.deleteMeal(meal)
    }

    override suspend fun getMealById(mealId: String): MealEntity? {
        return dao.getMealById(mealId)
    }
}