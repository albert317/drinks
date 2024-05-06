package com.albert.infinitespirit.features.onboarding.data.repository

import com.albert.infinitespirit.features.onboarding.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.onboarding.domain.Drink
import com.albert.infinitespirit.features.onboarding.usecase.repository.DrinkRepository
import javax.inject.Inject


class DrinkRepositoryImpl @Inject constructor(private val drinkDataSource: DrinkDataSource) :
    DrinkRepository {
    override suspend fun getDrink(id: String): Drink? {
        return drinkDataSource.getDrink(id)
    }

    override suspend fun getDrinks(): List<Drink> {
        return drinkDataSource.getDrinks()
    }
}