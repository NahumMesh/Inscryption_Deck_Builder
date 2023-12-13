package com.example.inscryptiondeckbuilder.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.screens.CardScreen
import com.example.inscryptiondeckbuilder.screens.DeckScreen
import com.example.inscryptiondeckbuilder.screens.ScrapeCard
import com.example.inscryptiondeckbuilder.screens.ScrapeDeck
import com.example.inscryptiondeckbuilder.screens.SettingsScreen

/**
 * HomeNavGraph function that holds the main navigation of the app once the user presses the login
 * button.
 */
@Composable
fun HomeNavGraph(navController: NavHostController, darkTheme: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        // Home route composable that shows all the cards available. Clicking on a card will track
        // its id so that it can show its specific details on its card details screen.
        composable(route = BottomBarScreen.Home.route) {
            CardScreen(onNavigateToCardScreen = {
                navController.navigate(route = "card_data/$it")
            })
        }

        // Composable for displaying the details of a single card clicked on by a user from the home
        // page. It keeps track of the id so that it can display the correct card data.
        composable(
            route = "card_data/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val cardId = it.arguments!!.getString("id").toString()
            ScrapeCard(cardId = cardId)
        }

        // Deck route composable that shows all the cards in the users deck. Clicking on a card will
        // also track its id so that it can show its specific details similar to the Home composable.
        composable(route = BottomBarScreen.Deck.route) {
            DeckScreen(onNavigateToCardScreen = {
                navController.navigate(route = "deck_data/$it")
            })
        }

        // Composable for displaying the details of a single card clicked on by a user from the deck
        // page. It keeps track of the id so that it can display the correct card data.
        composable(route = "deck_data/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val cardId = it.arguments!!.getString("id").toString()
            ScrapeDeck(cardId = cardId, navController)
        }

        // Settings route composable that allows the user to change the theme of the app between
        // dark and light.
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(
                darkTheme = darkTheme.value,
                onThemeUpdated = {darkTheme.value = !darkTheme.value}
            )
        }
    }
}



