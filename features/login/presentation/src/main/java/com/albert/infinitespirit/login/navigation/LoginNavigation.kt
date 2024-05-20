package com.albert.infinitespirit.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.albert.infinitespirit.login.LoginScreen

object LoginNavigation {
    const val LOCAL_ROUTE = "/login_navigation"
    const val LOGIN_SCREEN: String = "login_screen"
}

sealed class LoginRoutes(val route: String) {
    data object Login : LoginRoutes(LoginNavigation.LOGIN_SCREEN)
}

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    goToDrinks: () -> Unit,
) {
    navigation(
        startDestination = LoginRoutes.Login.route,
        route = LoginNavigation.LOCAL_ROUTE
    ) {
        composable(route = LoginRoutes.Login.route) {
            LoginScreen {
                goToDrinks()
            }
        }
    }
}