package com.albert.infinitespirit.features.drink.data.datasource.interfaces

import com.albert.infinitespirit.features.drink.domain.Drink

interface DrinkDataSource {
    suspend fun getDrink(id: String): Drink?
    suspend fun getDrinks(): List<Drink>
}