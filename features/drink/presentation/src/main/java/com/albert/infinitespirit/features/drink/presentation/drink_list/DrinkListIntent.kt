package com.albert.infinitespirit.features.drink.presentation.drink_list

import com.albert.infinitespirit.features.drink.domain.Drink

sealed class DrinkListIntent {
    data class SearchDrink(val query: String) : DrinkListIntent()
    data class SelectDrink(val drink: Drink) : DrinkListIntent()
    data object LoadDrinkList : DrinkListIntent()
}