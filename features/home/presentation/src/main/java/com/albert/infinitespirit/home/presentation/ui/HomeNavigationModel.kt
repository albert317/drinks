package com.albert.infinitespirit.home.presentation.ui

sealed class HomeNavigationModel {
    data object Home : HomeNavigationModel()
    data object Saved : HomeNavigationModel()
    data object LogOut : HomeNavigationModel()
    data object Settings : HomeNavigationModel()
}