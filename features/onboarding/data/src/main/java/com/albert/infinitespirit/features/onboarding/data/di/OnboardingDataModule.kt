package com.albert.infinitespirit.features.onboarding.data.di

import com.albert.infinitespirit.features.onboarding.data.datasource.DrinkDataSourceImpl
import com.albert.infinitespirit.features.onboarding.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.onboarding.data.repository.DrinkRepositoryImpl
import com.albert.infinitespirit.features.onboarding.usecase.repository.DrinkRepository
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