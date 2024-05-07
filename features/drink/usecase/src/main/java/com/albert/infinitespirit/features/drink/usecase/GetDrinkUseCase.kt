package com.albert.infinitespirit.features.drink.usecase

import com.albert.infinitespirit.features.drink.usecase.repository.DrinkRepository
import javax.inject.Inject

class GetDrinkUseCase @Inject constructor(private val repository: DrinkRepository) {
    suspend operator fun invoke(id: String) = repository.getDrink(id)
}