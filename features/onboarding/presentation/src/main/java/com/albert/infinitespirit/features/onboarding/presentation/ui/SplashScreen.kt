package com.albert.infinitespirit.features.onboarding.presentation.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.albert.infinitespirit.features.onboarding.presentation.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(goToLogin: () -> Unit, goToOnboarding: () -> Unit, goToHome: () -> Unit) {
    SplashContent()
    LaunchedEffect(key1 = true) {
        delay(1000L)
        goToHome()
    }
}

@Composable
fun SplashContent() {
    val alpha: Float by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ), label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = "App Icon",
            modifier = Modifier.alpha(alpha)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashContent()
}