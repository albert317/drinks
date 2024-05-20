package com.albert.infinitespirit.features.onboarding.presentation.ui

import com.albert.infinitespirit.common.domain.Failure

data class SplashUiState(
    val isLoading: Boolean = false,
    val error: Failure? = null
)