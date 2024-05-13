package com.albert.infinitespirit.features.drink.presentation.drink_detail

import com.albert.infinitespirit.features.drink.domain.Drink

sealed class DrinkIntent {
    data class ShareDrink(val drink: Drink) : DrinkIntent()
    data class SaveFavorite(val drink: Drink) : DrinkIntent()
    data class RemoveFavorite(val drink: Drink) : DrinkIntent()
}