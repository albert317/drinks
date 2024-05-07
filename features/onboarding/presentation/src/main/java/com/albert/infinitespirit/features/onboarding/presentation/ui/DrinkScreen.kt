package com.albert.infinitespirit.features.onboarding.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DrinkScreen() {
    val myViewModel: DrinkViewModel= hiltViewModel()
    val state by myViewModel.state.collectAsState()

    Text(text = "Drink Screen ${state.drink?.name}")
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}