package com.example.cookwhatyouhave.di.modules

import com.example.cookwhatyouhave.data.repository.MealRepositoryImpl
import com.example.cookwhatyouhave.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealRepository(impl: MealRepositoryImpl): MealRepository = impl
}