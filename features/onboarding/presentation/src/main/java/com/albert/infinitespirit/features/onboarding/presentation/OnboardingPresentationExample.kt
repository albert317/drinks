package com.albert.infinitespirit.features.onboarding.presentation

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.albert.infinitespirit.features.onboarding.domain.MyClass

class OnboardingPresentationExample {
    fun example(): String {
        val myClass = MyClass()

        return "OnboardingPresentationExample + ${myClass.example()} "
    }
}

@Composable
fun ExampleExterno2() {
    TextField(value = "ExampleExterno2", onValueChange = { })
}