package com.albert.infinitespirit.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.albert.infinitespirit.home.presentation.ui.HomeScreen

object HomeNavigation {
    const val LOCAL_ROUTE = "/home_app_navigation"
    const val HOME_ROUTE = "home"
}

sealed class HomeRoutes(val route: String) {
    data object Home : HomeRoutes(HomeNavigation.HOME_ROUTE)
}

fun NavGraphBuilder.homeNavGraph(
    navController:NavHostController,
    onNavigateToSave: (String) -> Unit,
    goToDrink:(String) -> Unit,
) {
    navigation(
        startDestination = HomeRoutes.Home.route,
        route = HomeNavigation.LOCAL_ROUTE
    ) {
        composable(HomeRoutes.Home.route) {
            HomeScreen(goToDrink)
        }
    }
}