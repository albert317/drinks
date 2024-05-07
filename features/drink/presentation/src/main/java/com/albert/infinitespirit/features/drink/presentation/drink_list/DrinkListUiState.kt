package com.albert.infinitespirit.features.drink.presentation.drink_list

import com.albert.infinitespirit.features.drink.domain.Drink

data class DrinkListUiState(
    val drinks: List<Drink> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)