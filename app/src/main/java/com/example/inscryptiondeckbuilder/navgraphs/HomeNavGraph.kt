package com.example.inscryptiondeckbuilder.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.screens.ScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            ScreenContent(
                name = BottomBarScreen.Home.route,
                onClick = {
                    navController.navigate(Graph.CARD)
                }
            )
        }

        composable(route = BottomBarScreen.Deck.route) {
            ScreenContent(
                name = BottomBarScreen.Deck.route,
                onClick = { }
            )
        }

        composable(route = BottomBarScreen.Settings.route) {
            ScreenContent(
                name = BottomBarScreen.Settings.route,
                onClick = { }
            )
        }

        cardNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.cardNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CARD,
        startDestination = CardScreen.CardInfo.route
    ) {
        composable(route = CardScreen.CardInfo.route) {
            ScreenContent(name = CardScreen.CardInfo.route) {
                navController.popBackStack(
                    route = BottomBarScreen.Home.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class CardScreen(val route: String) {
    object CardInfo : CardScreen(route = "CARD INFO")
}