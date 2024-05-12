package com.albert.infinitespirit.features.onboarding.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.albert.infinitespirit.features.onboarding.presentation.navigation.OnboardingNavigation.LOCAL_ROUTE
import com.albert.infinitespirit.features.onboarding.presentation.navigation.OnboardingNavigation.ONBOARDING_SCREEN
import com.albert.infinitespirit.features.onboarding.presentation.navigation.OnboardingNavigation.SPLASH_SCREEN
import com.albert.infinitespirit.features.onboarding.presentation.ui.OnboardingScreen
import com.albert.infinitespirit.features.onboarding.presentation.ui.SplashScreen

object OnboardingNavigation {
    const val LOCAL_ROUTE = "/onboarding_navigation"
    const val ONBOARDING_SCREEN: String = "onboarding_screen"
    const val SPLASH_SCREEN: String = "splash_screen"
}

sealed class OnboardingRoutes(val route: String) {
    data object Splash : OnboardingRoutes(SPLASH_SCREEN)
    data object Onboarding : OnboardingRoutes(ONBOARDING_SCREEN)
}

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController,
    onNavigateToHome: () -> Unit,
) {
    navigation(
        startDestination = OnboardingRoutes.Splash.route,
        route = LOCAL_ROUTE
    ) {
        composable(route = OnboardingRoutes.Splash.route) {
            SplashScreen(
                goToLogin = {
                    navController.navigate(OnboardingRoutes.Onboarding.route)
                },
                goToOnboarding = {
                    navController.navigate(OnboardingRoutes.Onboarding.route)
                },
                goToHome = {
                    onNavigateToHome()
                }
            )
        }
        composable(route = OnboardingRoutes.Onboarding.route) {
            OnboardingScreen()
        }
    }

}