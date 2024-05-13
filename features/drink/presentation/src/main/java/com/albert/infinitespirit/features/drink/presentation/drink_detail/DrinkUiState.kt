package com.albert.infinitespirit.features.drink.presentation.drink_detail

import com.albert.infinitespirit.common.domain.Failure
import com.albert.infinitespirit.features.drink.domain.Drink

data class DrinkUiState(
    val drink: Drink? = null,
    val isLoading: Boolean = false,
    val error: Failure? = null
)