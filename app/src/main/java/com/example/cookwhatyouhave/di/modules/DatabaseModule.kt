package com.example.cookwhatyouhave.di.modules

import android.content.Context
import androidx.room.Room
import com.example.cookwhatyouhave.framework.dao.MealDao
import com.example.cookwhatyouhave.framework.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            klass = AppDatabase::class.java,
            name = "meal_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMealDao(database:AppDatabase): MealDao{
        return database.mealDao()
    }
}