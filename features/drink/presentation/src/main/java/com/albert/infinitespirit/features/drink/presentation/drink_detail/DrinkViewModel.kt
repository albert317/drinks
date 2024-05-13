package com.albert.infinitespirit.features.drink.presentation.drink_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.common.domain.Failure
import com.albert.infinitespirit.common.domain.Result
import com.albert.infinitespirit.features.drink.usecase.GetDrinkUseCase
import com.albert.infinitespirit.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinkUseCase: GetDrinkUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DrinkUiState, DrinkEvent, DrinkIntent, DrinkNavigationModel>() {

    init {
        observeDrink()
    }

    override fun createInitialState(): DrinkUiState {
        return DrinkUiState()
    }

    override suspend fun handleIntent(intent: DrinkIntent) {
        when (intent) {
            is DrinkIntent.ShareDrink -> {}
            is DrinkIntent.SaveFavorite -> {}
            is DrinkIntent.RemoveFavorite -> {}
        }
    }

    private fun observeDrink() {
        val id = savedStateHandle.get<String>("idDrink")
        if (id != null) {
            setUiState { copy(isLoading = true) }
            viewModelScope.launch {
                try {
                    getDrinkUseCase(id).collect { result ->
                        when (result) {
                            is Result.Success -> {
                                setUiState { copy(drink = result.data, isLoading = false) }
                            }

                            is Result.Error -> {
                                setUiState {
                                    copy(
                                        isLoading = false, error = result.failure
                                    )
                                }
                                setEffect(DrinkEvent.ShowAlert(result.failure))
                            }
                        }
                    }
                } catch (e: Exception) {
                    setUiState {
                        copy(
                            isLoading = false,
                            error = Failure.CustomError(e.message ?: "Unknown Error")
                        )
                    }
                    setEffect(
                        DrinkEvent.ShowAlert(
                            Failure.CustomError(e.message ?: "Unknown Error")
                        )
                    )
                }
            }
        }
    }
}