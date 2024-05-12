package com.albert.infinitespirit.features.drink.presentation.drink_list

sealed class DrinkNavigationModel {
    data object DrinkList : DrinkNavigationModel()
    data class DrinkDetail(val idDrink: String) : DrinkNavigationModel()
}