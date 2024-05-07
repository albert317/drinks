package com.albert.infinitespirit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.albert.infinitespirit.designsystem.Desing
import com.albert.infinitespirit.features.onboarding.presentation.ExampleExterno
import com.albert.infinitespirit.features.onboarding.presentation.ExampleExterno2
import com.albert.infinitespirit.features.onboarding.presentation.OnboardingPresentationExample
import com.albert.infinitespirit.features.onboarding.presentation.ui.DrinkScreen
import com.albert.infinitespirit.features.onboarding.presentation.ui.Example
import com.albert.infinitespirit.features.onboarding.presentation.ui.Greeting2
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
                    val example = OnboardingPresentationExample()
                    Greeting("Android ${example.example()}")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {


        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Example222()
    }
}

@Composable
fun Example222() {
    val example = OnboardingPresentationExample()

    Text(text = "Example ${example.example()}")
    ExampleExterno2()
    Desing()
    DrinkScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InfiniteSpiritTheme {
        Greeting("Android")
    }
}