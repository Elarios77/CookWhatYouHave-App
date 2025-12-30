package com.example.cookwhatyouhave.data.repository

import com.example.cookwhatyouhave.data.datasource.LocalDataSource
import com.example.cookwhatyouhave.data.datasource.RemoteDataSource
import com.example.cookwhatyouhave.data.mapper.MealDtoMapper
import com.example.cookwhatyouhave.data.mapper.MealEntityMapper
import com.example.cookwhatyouhave.domain.model.MealItem
import com.example.cookwhatyouhave.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dtoMapper: MealDtoMapper,
    private val entityMapper: MealEntityMapper
) : MealRepository {

    override suspend fun getMealsByIngredient(ingredient: String): Result<List<MealItem>> {
        val dtoWrapper =
            remoteDataSource.fetchMealsByIngredient(ingredient) ?: return Result.failure(
                (Exception("Network error"))
            )
        val dtoList = dtoWrapper.meals ?: emptyList()
        val domainList = dtoList.map { dtoMapper(it) }
        return Result.success(domainList)
    }

    override suspend fun getMealsByCategory(category: String): Result<List<MealItem>> {
        val dtoWrapper = remoteDataSource.fetchMealsByCategory(category) ?: return Result.failure(
            Exception("Network error"))

        val dtoList = dtoWrapper.meals?: emptyList()
        val domainList = dtoList.map { dtoMapper(it) }
        return Result.success(domainList)
    }

    override suspend fun getMealDetails(id: String): Result<List<MealItem>> {
        val dtoWrapper = remoteDataSource.fetchMealDetails(id)
            ?: return Result.failure(Exception("Network error"))
        val dtoList = dtoWrapper.meals
        return if(!dtoList.isNullOrEmpty()){
            val domainList = dtoList.map { dtoMapper(it) }
            Result.success(domainList)
        }else{
            Result.failure(Exception("Meal not found"))
        }
    }

    override fun getFavoriteMeals(): Flow<List<MealItem>> {
        return localDataSource.getAllMeals().map { entities ->
            entities.mapNotNull { entity ->
                entityMapper(entity)
            }
        }
    }

    override suspend fun saveMeal(meal: MealItem) {
        val entity = entityMapper(meal)
        entity?.let {
            localDataSource.insertMeal(entity)
        }
    }

    override suspend fun deleteMeal(meal: MealItem) {
        val entity = entityMapper(meal)
        entity?.let {
            localDataSource.deleteMeal(entity)
        }
    }

    override suspend fun getMealById(id: String): MealItem? {
        val entity = localDataSource.getMealById(id)
        return entityMapper(entity)
    }
}