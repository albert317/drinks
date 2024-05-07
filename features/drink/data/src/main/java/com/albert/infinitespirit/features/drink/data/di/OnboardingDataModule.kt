package com.albert.infinitespirit.features.drink.data.di

import com.albert.infinitespirit.features.drink.data.datasource.DrinkDataSourceImpl
import com.albert.infinitespirit.features.drink.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.drink.data.repository.DrinkRepositoryImpl
import com.albert.infinitespirit.features.drink.usecase.repository.DrinkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class OnboardingDataModule {

}

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingDataBinds {
    @Binds
    abstract fun bindDrinkDataSource(drinkDataSourceImpl: DrinkDataSourceImpl): DrinkDataSource

    @Binds
    abstract fun bindDrinkRepository(drinkRepositoryImpl: DrinkRepositoryImpl): DrinkRepository
}
