package com.albert.infinitespirit.features.drink.presentation.drink_detail

sealed class DrinkNavigationModel {
    data object FavoriteDrinks : DrinkNavigationModel()
}