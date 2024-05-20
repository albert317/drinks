package com.albert.infinitespirit.home.presentation.ui

import com.albert.infinitespirit.common.domain.Failure

sealed class HomeEvent {
    data class showAlert(val error: Failure) : HomeEvent()
}