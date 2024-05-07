package com.albert.infinitespirit.features.drink.usecase.repository

import com.albert.infinitespirit.features.drink.domain.Drink

interface DrinkRepository {
    suspend fun getDrink(id: String): Drink?
    suspend fun getDrinks(): List<Drink>
}