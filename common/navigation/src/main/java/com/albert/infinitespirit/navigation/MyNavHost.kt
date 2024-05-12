package com.albert.infinitespirit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.albert.infinitespirit.features.drink.presentation.navigation.DrinkNavigation
import com.albert.infinitespirit.features.drink.presentation.navigation.DrinkRoutes
import com.albert.infinitespirit.features.drink.presentation.navigation.drinkNavGraph
import com.albert.infinitespirit.features.onboarding.presentation.navigation.OnboardingNavigation
import com.albert.infinitespirit.features.onboarding.presentation.navigation.onboardingNavGraph

@Composable
fun MyNavHost(
    navController: NavHostController,
) {
    val startDestination = OnboardingNavigation.LOCAL_ROUTE
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        onboardingNavGraph(navController = navController) {
            navController.navigate(DrinkRoutes.DrinkList.route)
        }

        drinkNavGraph(navController = navController) {
            //navController.navigate()
        }


    }
}