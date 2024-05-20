package com.albert.infinitespirit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.albert.infinitespirit.features.drink.presentation.navigation.DrinkRoutes
import com.albert.infinitespirit.features.drink.presentation.navigation.drinkNavGraph
import com.albert.infinitespirit.features.onboarding.presentation.navigation.OnboardingNavigation
import com.albert.infinitespirit.features.onboarding.presentation.navigation.OnboardingRoutes
import com.albert.infinitespirit.features.onboarding.presentation.navigation.onboardingNavGraph
import com.albert.infinitespirit.home.presentation.navigation.HomeNavigation
import com.albert.infinitespirit.home.presentation.navigation.homeNavGraph
import com.albert.infinitespirit.login.navigation.LoginNavigation
import com.albert.infinitespirit.login.navigation.loginNavGraph

@Composable
fun MyNavHost(
    navController: NavHostController,
) {
    val startDestination = OnboardingNavigation.LOCAL_ROUTE
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        onboardingNavGraph(navController = navController, {
            /*navController.navigate(DrinkRoutes.DrinkList.route) {
                popUpTo(OnboardingRoutes.Splash.route) { inclusive = true }
            }*/
            navController.navigate(HomeNavigation.LOCAL_ROUTE) {
                popUpTo(OnboardingRoutes.Splash.route) { inclusive = true }
            }
        }, {
            navController.navigate(LoginNavigation.LOCAL_ROUTE)
        })

        loginNavGraph(navController = navController) {
            /*navController.navigate(DrinkRoutes.DrinkList.route){

            }*/
            navController.navigate(HomeNavigation.LOCAL_ROUTE)
        }

        homeNavGraph(navController = navController, {

        }, goToDrink = {
            navController.navigate(DrinkRoutes.DrinkDetail.route(it))
        })

        drinkNavGraph(navController = navController) {
            //navController.navigate()
        }

    }
}