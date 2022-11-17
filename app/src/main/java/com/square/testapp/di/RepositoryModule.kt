package com.square.testapp.di

import com.square.testapp.data.repository.EmployeeRepositoryImpl
import com.square.testapp.domain.repository.EmployeeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEmployeeRepository(
        weatherRepositoryImpl: EmployeeRepositoryImpl
    ): EmployeeRepository
}