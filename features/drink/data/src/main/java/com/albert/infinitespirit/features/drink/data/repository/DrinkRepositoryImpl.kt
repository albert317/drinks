package com.albert.infinitespirit.features.drink.data.repository

import com.albert.infinitespirit.common.domain.Result
import com.albert.infinitespirit.features.drink.data.datasource.interfaces.DrinkDataSource
import com.albert.infinitespirit.features.drink.domain.Drink
import com.albert.infinitespirit.features.drink.usecase.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(private val drinkDataSource: DrinkDataSource) :
    DrinkRepository {
    override suspend fun getDrink(id: String): Flow<Result<Drink?>> {
        return drinkDataSource.getDrink(id)
    }

    override suspend fun getDrinks(): Flow<Result<List<Drink>>> {
        return drinkDataSource.getDrinks()
    }
}