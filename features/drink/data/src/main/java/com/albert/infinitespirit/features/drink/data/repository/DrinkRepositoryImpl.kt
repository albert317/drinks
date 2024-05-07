package com.albert.infinitespirit.features.drink.data.repository

import com.albert.infinitespirit.features.drink.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.drink.domain.Drink
import com.albert.infinitespirit.features.drink.usecase.repository.DrinkRepository
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