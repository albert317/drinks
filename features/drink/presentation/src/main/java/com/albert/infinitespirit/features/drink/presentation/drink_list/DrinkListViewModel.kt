package com.albert.infinitespirit.features.drink.presentation.drink_list

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.common.domain.Failure
import com.albert.infinitespirit.common.domain.Result
import com.albert.infinitespirit.features.drink.domain.Drink
import com.albert.infinitespirit.features.drink.usecase.GetDrinkListUseCase
import com.albert.infinitespirit.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val getDrinkListUseCase: GetDrinkListUseCase
) : BaseViewModel<DrinkListUiState, DrinkListEvent, DrinkListIntent>() {
    private var originalDrinks: List<Drink> = listOf()
    private var searchQuery: String = ""

    private var _navigateToDetailEvent = MutableSharedFlow<DrinkNavigationModel>()
    val navigateToDetailEvent = _navigateToDetailEvent.asSharedFlow()

    override fun createInitialState(): DrinkListUiState {
        return DrinkListUiState()
    }

    override suspend fun handleIntent(intent: DrinkListIntent) {
        when (intent) {
            is DrinkListIntent.LoadDrinkList -> observeDrinkList()
            is DrinkListIntent.SelectDrink -> navigateToDetail(intent.drink)
            is DrinkListIntent.SearchDrink -> {}
        }
    }

    private fun observeDrinkList() {
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            try {
                getDrinkListUseCase().collect { result ->
                    when (result) {
                        is Result.Success -> {
                            originalDrinks = result.data
                            setUiState { copy(drinks = result.data, isLoading = false) }
                            searchDrinks()
                        }

                        is Result.Error -> {
                            setUiState {
                                copy(
                                    isLoading = false, error = Throwable(result.failure.toString())
                                )
                            }
                            setEffect(DrinkListEvent.showAlert(result.failure))
                        }
                    }
                }
            } catch (e: Exception) {
                setUiState { copy(isLoading = false, error = Throwable(e.message)) }
                setEffect(
                    DrinkListEvent.showAlert(
                        Failure.CustomError(e.message ?: "Unknown Error")
                    )
                )
            }
        }
    }

    fun searchDrinks(searchQuery: String = this.searchQuery) {
        this.searchQuery = searchQuery
        if (searchQuery.isEmpty()) {
            setUiState { copy(drinks = originalDrinks) }
        } else {
            val filteredDrinks =
                originalDrinks.filter { it.name?.contains(searchQuery, ignoreCase = true) == true }
            setUiState { copy(drinks = filteredDrinks) }
        }
    }

    private suspend fun navigateToDetail(drink: Drink) {
        Log.d("DrinkListViewModel", "navigateToDetail: ${drink.name}")
        _navigateToDetailEvent.emit(DrinkNavigationModel.DrinkDetail(drink.name ?: ""))
    }
}