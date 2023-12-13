package com.example.inscryptiondeckbuilder.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inscryptiondeckbuilder.screens.HomeScreen

/**
 * Root navgraph that sets the startDestination to the authentication navgraph
 */
@Composable
fun RootNavGraph(navController: NavHostController, darkTheme: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen(darkTheme = darkTheme)
        }
    }
}

/**
 * Graph object used for simplifying the call of navgraph routes.
 */
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}