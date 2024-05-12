package com.albert.infinitespirit.features.drink.presentation.drink_list

import com.albert.infinitespirit.common.domain.Failure


sealed class DrinkListEvent {
    data class showAlert(val error: Failure) : DrinkListEvent()
}