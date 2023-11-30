package com.example.inscryptiondeckbuilder

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Nav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Main"){
        composable(route = "Main"){
            MainScreen(navController)
        }

        composable(route = "Deck"){
            DeckScreen(navController)
        }

        composable(route = "SingleCard"){
            SingleCardScreen(navController)
        }
    }
}