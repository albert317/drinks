package com.albert.infinitespirit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.albert.infinitespirit.features.drink.presentation.drink_list.DrinkListContent
import com.albert.infinitespirit.navigation.MyNavHost
import com.albert.infinitespirit.ui.theme.InfiniteSpiritTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfiniteSpiritTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting() {
    Column {
        DrinkListContent(
            drinks = listOf(
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 1"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 2"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 3"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 4"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 5"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 6"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 7"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 8"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 9"),
                com.albert.infinitespirit.features.drink.domain.Drink("Drink 10"),
            ),
            searchFunction = {}
        )
    }
}