package com.albert.infinitespirit.features.drink.presentation.drink_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.features.drink.usecase.GetDrinkListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val getDrinkListUseCase: GetDrinkListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DrinkListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getDrinkList()
    }

    fun getDrinkList() {
        _uiState.value = DrinkListUiState(isLoading = true)
        viewModelScope.launch {
            val drinks = getDrinkListUseCase()
            _uiState.value = DrinkListUiState(drinks = drinks)
        }
    }
}