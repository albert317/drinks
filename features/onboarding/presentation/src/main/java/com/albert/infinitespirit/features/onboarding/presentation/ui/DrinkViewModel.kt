package com.albert.infinitespirit.features.onboarding.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.features.onboarding.domain.Drink
import com.albert.infinitespirit.features.onboarding.usecase.repository.GetDrinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinkUseCase: GetDrinkUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DrinkUiState())
    val state = _state.asStateFlow()

    init {
        getDrink("8h2Y1JvkNv2mXFEfYHAx")
    }

    fun getDrink(id: String) {
        _state.value = DrinkUiState(isLoading = true)
        viewModelScope.launch {
            val drink = getDrinkUseCase(id)
            _state.value = DrinkUiState(drink = drink)
        }
    }
}

data class DrinkUiState(
    val isLoading: Boolean = false,
    val drink: Drink? = null,
    val error: Throwable? = null,
)