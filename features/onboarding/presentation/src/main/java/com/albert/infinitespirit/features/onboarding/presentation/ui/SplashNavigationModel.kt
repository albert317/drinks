package com.albert.infinitespirit.features.onboarding.presentation.ui

sealed class SplashNavigationModel {
    data object Onboarding : SplashNavigationModel()
    data object Drinks : SplashNavigationModel()
    data object Login : SplashNavigationModel()
}