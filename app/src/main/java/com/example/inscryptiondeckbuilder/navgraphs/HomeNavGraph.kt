package com.example.inscryptiondeckbuilder.navgraphs

import CardScreen
import ScrapeCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.screens.DeckScreen
import com.example.inscryptiondeckbuilder.screens.ScrapeDeck
import com.example.inscryptiondeckbuilder.screens.SettingsScreen


@Composable
fun HomeNavGraph(navController: NavHostController, darkTheme: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            CardScreen(onNavigateToCardScreen = {
                navController.navigate(route = "card_data/$it")
            })
        }

        composable(route = "card_data/{id}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                }
            )
        ) {
            val cardId = it.arguments!!.getString("id").toString()
            ScrapeCard(cardId = cardId)
        }

        composable(route = BottomBarScreen.Deck.route) {
            DeckScreen(onNavigateToCardScreen = {
                navController.navigate(route = "deck_data/$it")
            })
        }

        composable(route = "deck_data/{id}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                }
            )
        ) {
            val cardId = it.arguments!!.getString("id").toString()
            ScrapeDeck(cardId = cardId, navController)
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(
                darkTheme = darkTheme.value,
                onThemeUpdated = {darkTheme.value = !darkTheme.value}
            )
        }
    }
}



