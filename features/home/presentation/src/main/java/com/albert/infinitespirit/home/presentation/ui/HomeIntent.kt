package com.albert.infinitespirit.home.presentation.ui

sealed class HomeIntent {
    data object LoadDataUser: HomeIntent()
    data object GoToSaved: HomeIntent()
    data object GoToLogOut: HomeIntent()
    data object GoToSettings: HomeIntent()
}