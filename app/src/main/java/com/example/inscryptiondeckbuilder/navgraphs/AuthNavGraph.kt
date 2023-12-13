package com.example.inscryptiondeckbuilder.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inscryptiondeckbuilder.screens.LoginContent

/**
 * authNavGraph function that displays a Login and SignUp button. Currently this is used purely as
 * a mock for authentication functionality. Clicking the SignUp button leads to a blank page but
 * clicking the Login button takes you to the home page of the app which would simulate a real login.
 */
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginContent(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }

        composable(route = AuthScreen.SignUp.route) {

        }
    }
}

/**
 * AuthScreen sealed class used for simplifying the call of the login and signup screen routes.
 */
sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN UP")
}