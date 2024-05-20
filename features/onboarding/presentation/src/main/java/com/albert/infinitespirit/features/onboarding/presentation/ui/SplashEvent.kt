package com.albert.infinitespirit.features.onboarding.presentation.ui

import com.albert.infinitespirit.common.domain.Failure

sealed class SplashEvent {
    data class showAlert(val error: Failure) : SplashEvent()
}