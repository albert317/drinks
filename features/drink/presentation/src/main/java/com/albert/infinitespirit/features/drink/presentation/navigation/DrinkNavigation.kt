package com.albert.infinitespirit.features.drink.presentation.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.albert.infinitespirit.features.drink.presentation.drink_list.DrinkListScreen
import com.albert.infinitespirit.features.drink.presentation.navigation.DrinkNavigation.DRINK_DETAIL_SCREEN
import com.albert.infinitespirit.features.drink.presentation.navigation.DrinkNavigation.DRINK_LIST_SCREEN

object DrinkNavigation {
    const val LOCAL_ROUTE = "/drink_navigation"
    const val DRINK_LIST_SCREEN: String = "drinks_list_screen"
    const val DRINK_DETAIL_SCREEN: String = "drink_detail_screen"
}

sealed class DrinkRoutes(val route: String) {
    data object DrinkList : DrinkRoutes(DRINK_LIST_SCREEN)
    data object DrinkDetail : DrinkRoutes("$DRINK_DETAIL_SCREEN/{${DrinkNavArgs.IdDrink.key}}") {
        fun route(idDrink: String): String {
            return "$DRINK_DETAIL_SCREEN/$idDrink"
        }
    }
}

private enum class DrinkNavArgs(val key: String) {
    IdDrink("idDrink"),
}

fun NavGraphBuilder.drinkNavGraph(
    navController: NavHostController,
    onNavigateToSave: (String) -> Unit,
) {
    navigation(
        startDestination = DrinkRoutes.DrinkList.route,
        route = DrinkNavigation.LOCAL_ROUTE
    ) {
        composable(route = DrinkRoutes.DrinkList.route) {
            DrinkListScreen { idDrink ->
                navController.navigate(DrinkRoutes.DrinkDetail.route(idDrink))
            }
        }
        composable(
            route = DrinkRoutes.DrinkDetail.route,
            arguments = listOf(
                navArgument(DrinkNavArgs.IdDrink.key) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val idDrink = backStackEntry.arguments?.getString(DrinkNavArgs.IdDrink.key)
            Text(text = "Drink Detail: $idDrink")
        }
    }
}