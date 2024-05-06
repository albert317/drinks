package com.albert.infinitespirit.features.onboarding.data.datasource.interfaces

import com.albert.infinitespirit.features.onboarding.domain.Drink

interface DrinkDataSource {
    suspend fun getDrink(id: String): Drink?
    suspend fun getDrinks(): List<Drink>
}