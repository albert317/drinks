package com.albert.infinitespirit.features.onboarding.usecase.repository

import com.albert.infinitespirit.features.onboarding.domain.Drink

interface DrinkRepository {
    suspend fun getDrink(id: String): Drink?
    suspend fun getDrinks(): List<Drink>
}