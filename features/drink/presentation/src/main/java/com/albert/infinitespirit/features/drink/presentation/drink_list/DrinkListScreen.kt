package com.albert.infinitespirit.features.drink.presentation.drink_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.albert.infinitespirit.features.drink.domain.Drink

@Composable
fun DrinkListScreen() {
    val viewModel: DrinkListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    DrinkListContent(
        drinks = state.drinks,
    )
}

@Composable
fun DrinkListContent(
    drinks: List<Drink>
) {

    drinks.map {
        DrinkItem(drink = it)
    }

}

@Composable
fun DrinkItem(
    drink: Drink
) {
    drink.name?.let {
        Text(
            it
        )
    }
}