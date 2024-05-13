package com.albert.infinitespirit.features.drink.presentation.drink_detail

import com.albert.infinitespirit.common.domain.Failure

sealed class DrinkEvent {
    data class ShowAlert(val error: Failure) : DrinkEvent()
}