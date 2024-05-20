package com.albert.infinitespirit.features.onboarding.presentation.ui

sealed class SplashIntent {
    object getVersion : SplashIntent()
    object hasSession : SplashIntent()
}