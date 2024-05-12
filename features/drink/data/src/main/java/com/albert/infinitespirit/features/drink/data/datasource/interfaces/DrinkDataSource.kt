package com.albert.infinitespirit.features.drink.data.datasource.interfaces

import com.albert.infinitespirit.common.domain.Result
import com.albert.infinitespirit.features.drink.domain.Drink
import kotlinx.coroutines.flow.Flow

interface DrinkDataSource {
    suspend fun getDrink(id: String): Flow<Result<Drink?>>
    suspend fun getDrinks(): Flow<Result<List<Drink>>>
}