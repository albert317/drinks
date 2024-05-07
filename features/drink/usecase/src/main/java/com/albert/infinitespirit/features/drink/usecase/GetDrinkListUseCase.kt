package com.albert.infinitespirit.features.drink.usecase

import com.albert.infinitespirit.features.drink.usecase.repository.DrinkRepository
import javax.inject.Inject

class GetDrinkListUseCase @Inject constructor(private val repository: DrinkRepository) {
    suspend operator fun invoke() = repository.getDrinks()
}