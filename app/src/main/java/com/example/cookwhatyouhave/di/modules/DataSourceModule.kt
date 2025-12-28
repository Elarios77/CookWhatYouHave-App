package com.example.cookwhatyouhave.di.modules

import com.example.cookwhatyouhave.data.datasource.LocalDataSource
import com.example.cookwhatyouhave.data.datasource.RemoteDataSource
import com.example.cookwhatyouhave.framework.datasource.LocalDataSourceImpl
import com.example.cookwhatyouhave.framework.datasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource
}