package com.albert.infinitespirit.home.presentation.ui

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val userName: String = "",
    val email: String = "",
    val photoUrl: String = ""
)