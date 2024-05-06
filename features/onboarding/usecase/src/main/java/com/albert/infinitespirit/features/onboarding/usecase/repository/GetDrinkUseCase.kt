package com.albert.infinitespirit.features.onboarding.usecase.repository

import javax.inject.Inject

class GetDrinkUseCase @Inject constructor(private val repository: DrinkRepository) {
    suspend operator fun invoke(id: String) = repository.getDrink(id)
}