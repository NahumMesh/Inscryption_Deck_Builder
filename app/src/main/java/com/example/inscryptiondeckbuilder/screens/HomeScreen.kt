package com.example.inscryptiondeckbuilder.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.navgraphs.HomeNavGraph

/**
 * The home screen creates the top and bottom bars.
 * It takes in the home nav graph which contains all of the screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(), darkTheme: MutableState<Boolean>) {
    val modifier = Modifier
    Scaffold(
        // Top bar that has a title.
        topBar = {
            // We assign the title as a box with modifiers and the actual text.
            TopAppBar(
                title = {
                    Box(
                        modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Inscryption Deck Builder")
                    }
                },

                // Modifier add some padding and round the edges of the top bar.
                modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),

                // Set the top bar to a desired hex colour.
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF697359)
                )
            )
        },
        bottomBar = {
                BottomBar(navController = navController)
            }
    )
    {
        HomeNavGraph(navController = navController, darkTheme = darkTheme)
    }

}

/**
 * The BottomBar Composable function gets a list of screens.
 * It then adds the items into each screen.
 */
@Composable
fun BottomBar(navController: NavHostController) {
    val modifier = Modifier
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Deck,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomAppBar(
            modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp)),
            containerColor = Color(0xFF697359)
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

/**
 * The RowScope Composable function adds the items into each NavBarItem.
 * It then navigates to each screen depending on what icon is clicked.
 */
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
        )
}